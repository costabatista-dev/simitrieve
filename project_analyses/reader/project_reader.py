import os
import sys
import re
import pandas as pd
class ProjectReader:

    def __init__(self, root, language):
       
        self.root = root
        self.language = language


    def getProjectStructure(self):
        
        structureList = []
        for path, subdirs, files in os.walk(self.root):
            for fileName in files:
                structure = os.path.join(path, fileName)
                self.filterProjectLanguage(structure, structureList)

        return structureList


    def filterProjectLanguage(self, structure, structureList=[]):
        
        if "python" in self.language:
            self.filterPythonProject(structure, structureList)

        elif "javascript" in self.language:
            self.filterJavascriptProject(structure, structureList)

        elif "java" in self.language:
            self.filterJavaProject(structure, structureList)



    def filterPythonProject(self, structure, structureList=[]):
       
        pythonPattern = re.compile(".+\.py$")
        pythonInitPattern = re.compile(".*__init__.py$")
        pythonCachePattern = re.compile(".*__pycache__.*")

        if pythonInitPattern.match(structure):
            return
        
        elif pythonCachePattern.match(structure):
            return
        
        elif pythonPattern.match(structure):
            structureList.append(structure)


    def filterJavaProject(self, structure, structureList=[]):
        
        javaPattern = re.compile(".+\.java$")
        javadocPattern = re.compile(".*package-info.java$")

        if javadocPattern.match(structure):
            return

        elif javaPattern.match(structure):
            structureList.append(structure)

    def filterJavascriptProject(self, structure, structureList=[]):

        javascriptPattern = re.compile(".+\.js$")

        if javascriptPattern.match(structure):
            print("aqui")
            structureList.append(structure)
