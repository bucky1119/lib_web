# -*- coding: utf-8 -*-
# @Time    : 2024/6/5
# @Author  : ChenSiGuang
# @FileName: db.py
# @Software: PyCharm
# @Description:数据库初始化代码
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import yaml
import os


def load_db_config():
    config_path = os.path.join(os.path.abspath(''), 'configs', 'application.yaml')
    with open(config_path, 'r', encoding='utf-8') as file:
        config = yaml.safe_load(file)
    return config['database']


db_config = load_db_config()
engine = create_engine(db_config['url'], pool_size=db_config['pool_size'], max_overflow=db_config['max_overflow'])
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

