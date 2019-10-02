#!/usr/bin/env python3
'''
Simple script to setup gradle workspace for forge modding in intellij
by RedstoneLP2
'''
import in_place
import os
import subprocess
import sys

def replaceText(filename, searchText, replacementText):
	with in_place.InPlace(filename) as file:
		for line in file:
			line = line.replace(searchText, replacementText)
			file.write(line)

def authorInput(number):
	authors = []
	for i in range(1,int(number)+1):
		authors.append(input("("+str(i)+")"+" Author Name: "))
	authorsString = ', '.join(authors)
	return authorsString

def callGradle():
	if os.name == "posix":
		subprocess.run(["./gradlew", "setupDecompWorkspace"])
	elif os.name == "nt":
		subprocess.run(["gradlew.bat", "setupDecompWorkspace"])

modname = input("Modname: ")
modid = input("Modid: ")
group = input("Group: ")
authornr = input("Number of authors (number): ")
description = input("Description (May be left empty): ")

authorsString = "[" + authorInput(authornr) + "]"
groupdirs = group.split(".")
modSrcPath = os.path.join("src", "main", "java", *groupdirs)
initJavaFilePath = os.path.join(modSrcPath, modname + ".java")

print(modname)
print(modid)
print(group)
print(authornr)
print(authorsString)

input("press enter to continue")
resourcesPath = os.path.join("src","main","resources")
os.makedirs(resourcesPath)
os.makedirs(modSrcPath)
replaceText("build.gradle", "#group", group)
replaceText("build.gradle", "#modname", modname)


initJavaFileContents = '''package '''+group+''';

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = '''+modname+'''.MODID, name = '''+modname+'''.NAME, version = '''+modname+'''.VERSION, acceptableRemoteVersions = "*")
public class '''+modname+''' {
    public static final String MODID = "'''+modid+'''";
    public static final String NAME = "'''+modname+'''";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLServerStartingEvent event) {
        logger.info("initalise FMLServerStartingEvent :" + NAME);
    }
}'''
mcmodInfoContents = '''[
{
  "modid": "'''+modid+'''",
  "name": "'''+modname+'''",
  "description": "'''+description+'''",
  "version": "1.0",
  "mcversion": "1.12",
  "url": "",
  "updateUrl": "",
  "authorList": '''+authorsString+''',
  "credits": "",
  "logoFile": "",
  "screenshots": [],
  "dependencies": []
}
]'''
packmcmetacontents = '''{
    "pack": {
        "description": "minecraft resources",
        "pack_format": 3,
        "_comment": "A pack_format of 3 should be used starting with Minecraft 1.11. All resources, including language files, should be lowercase (eg: en_us.lang). A pack_format of 2 will load your mod resources with LegacyV2Adapter, which requires language files to have uppercase letters (eg: en_US.lang)."
    }
}'''

f= open(initJavaFilePath, 'w')
f.write(initJavaFileContents)
f.close()
f= open(os.path.join(resourcesPath, "mcmod.info"), 'w')
f.write(mcmodInfoContents)
f.close()
f= open(os.path.join(resourcesPath, "pack.mcmeta"), 'w')
f.write(packmcmetacontents)
f.close()
callGradle()