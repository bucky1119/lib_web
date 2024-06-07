# -*- coding: utf-8 -*-
# @Time    : 2024/6/5
# @Author  : ChenSiGuang
# @FileName: celery_app.py
# @Software: PyCharm
# @Description:这段代码使用了Celery，这是一个分布式任务队列，允许你在多个机器上调度和执行异步任务。该代码的目标是根据网站名称动态加载相应的爬虫模块并执行爬取任务。

from celery import Celery
import importlib
import os
import sys

# 添加项目根目录到 Python 路径
sys.path.append('/path/to/crawlers')
# 添加项目根目录到 Python 路径
sys.path.append(os.path.dirname(os.path.abspath(__file__)))
os.getenv("REDISTOGO_URL", "redis://localhost:6379")

app = Celery('tasks', broker='redis://127.0.0.1:6379/0')


@app.task
def crawl_site(site_name, config):
    print(f"Starting crawl task for {site_name} with config {config}")  # 添加调试信息
    try:
        print("Before importing module")  # 添加调试信息
        module = importlib.import_module(f'crawlers.{site_name}_crawler')
        print(f"Imported module crawlers.{site_name}_crawler")  # 添加调试信息
        crawler_class = getattr(module, f'{site_name.capitalize()}Crawler')
        print(f"Got crawler class {site_name.capitalize()}Crawler")  # 添加调试信息
        print(f"Crawler class: {crawler_class}")  # 打印 crawler_class 信息
        crawler = crawler_class(config)
        print("Initialized crawler class")  # 添加调试信息
        crawler.crawl()
        print("Finished crawl task")  # 添加调试信息
    except Exception as e:
        print(f"Error in crawl_site task: {e}")