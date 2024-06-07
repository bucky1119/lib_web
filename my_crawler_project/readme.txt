1.安装依赖：
pip install -r requirements.txt
2.创建数据库表
运行以下脚本来创建数据库表：
from db import Base, engine
from models import WebData
Base.metadata.create_all(bind=engine)

3.启动 Celery Worker
确保 Redis 服务器正在运行，然后启动 Celery Worker：
celery -A celery_app worker --loglevel=info -P eventlet

4.运行调度器
运行调度器来调度爬虫任务：
python main.py
