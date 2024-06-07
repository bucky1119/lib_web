# -*- coding: utf-8 -*-
# @Time    : 2024/6/5
# @Author  : ChenSiGuang
# @FileName:
# @Software: PyCharm
# @Description:统一调度和执行

from celery_app import crawl_site
import yaml

def load_config():
    with open('configs/sites.yaml', 'r', encoding='utf-8') as file:
        config = yaml.safe_load(file)
        print("Loaded config:", config)  # 添加调试信息
        return config

def schedule_crawlers():
    config = load_config()
    for site_name, site_config in config['sites'].items():
        print(f"Scheduling crawl for {site_name} with config {site_config}")  # 添加调试信息
        crawl_site.delay(site_name, site_config)
        print(f"Task for {site_name} scheduled")  # 添加调试信息

if __name__ == '__main__':
    schedule_crawlers()
