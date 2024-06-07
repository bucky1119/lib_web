# -*- coding: utf-8 -*-
# @Time    : 2024/6/6
# @Author  : ChenSiGuang
# @FileName: test
# @Software: PyCharm
# @Description:
import yaml
import os


def load_db_config():
    config_path = os.path.join( os.path.abspath(''), 'configs', 'application.yaml')
    with open(config_path, 'r', encoding='utf-8') as file:
        return yaml.safe_load(file)


if __name__ == "__main__":
    db_config = load_db_config()
    print(db_config)
