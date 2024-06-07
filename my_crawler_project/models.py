# -*- coding: utf-8 -*-
# @Time    : 2024/6/5
# @Author  : ChenSiGuang
# @FileName: models
# @Software: PyCharm
# @Description:模型定义
from sqlalchemy import Column, String, Text, Enum, Date
from db import Base

class WebData(Base):
    __tablename__ = 'web_data_table'

    id = Column(String(20), primary_key=True)
    title = Column(Text, nullable=False)
    author = Column(String(100), default='')
    info_type = Column(Enum('T0', 'T1', 'T2', 'T3', 'T4'))
    post_agency = Column(String(80), default='')
    nation = Column(String(30), default='未知')
    date = Column(Date)
    link_url = Column(Text)
    domain = Column(Enum('F0', 'F1', 'F2', 'F3', 'F4', 'F5', 'F6', 'F7', 'F8', 'F9'), default='F0')
    subject = Column(Enum('非F1领域', 'AG0', 'AG1', 'AG2', 'AG3', 'AG4', 'AG5', 'AG6', 'AG7', 'AG8', 'AG9', 'AG10', 'AG11'))
    text = Column(Text, nullable=False)
