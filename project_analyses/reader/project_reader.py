import os
import sys
import re
import pandas as pd
class ProjectReader:

    def __init__(self, root):
        self.root = root


    def getProjectStructure(self):
        structure = []
        for path, subdirs, files in os.walk(self.root):
            for name in files:
                structure.append(os.path.join(path, name))
        return structure


    