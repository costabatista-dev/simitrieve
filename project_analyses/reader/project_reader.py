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
                #self.filterPythonProject(os.path.join(path, name))
                #structure.append(os.path.join(path, name))
        return structureList


    def filterProjectLanguage(self, structure, structureList=[]):
        if "python" in self.language:
            self.filterPythonProject(structure, structureList)



    def filterPythonProject(self, structure, structureList=[]):
        pythonPattern = re.compile(".+\.py$")
        pythonInitPattern = re.compile(".+__init__.py$")
        pythonCachePattern = re.compile(".+__pycache__.*")

        if pythonInitPattern.match(structure):
            return
        elif pythonCachePattern.match(structure):
            return
        elif pythonPattern.match(structure):
            structureList.append(structure)


