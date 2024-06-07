# -*- coding: utf-8 -*-
# @Time    : 2024/6/5
# @Author  : ChenSiGuang
# @FileName: site1_crawler.py
# @Software: PyCharm
# @Comment : selenium框架 Mysql数据库 澳大利亚(静态页面)
# https://www.industry.gov.au/publications?pub-topic=2906&pub-category=All&publisher=All&field_date_value=All
import logging
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from datetime import datetime
from sqlalchemy.orm import Session
from db import SessionLocal
from models import WebData


class Site1Crawler:
    def __init__(self, config):
        print("Initializing Site1Crawler with config:", config)  # 添加调试信息
        self.url = config['url']

        options = Options()
        prefs = {"profile.managed_default_content_settings.images": 2}
        options.add_experimental_option("prefs", prefs)
        options.add_argument('--start-maximized')
        #options.add_argument('--headless')  # 使用headless模式运行,不知道为什么使用headless模式会报错


        # # 确认 chromedriver 路径正确
        # chromedriver_path = 'D:/CSsoftware/anaconda3/envs/webcrawler/Scripts/chromedriver.exe'
        # service = Service(chromedriver_path)
        # print(f"Using chromedriver at {chromedriver_path}")  # 添加调试信息
        try:
            print("Initializing WebDriver...")  # 添加调试信息
            # self.driver = webdriver.Chrome(service=service, options=options)
            self.driver = webdriver.Chrome(options=options)
            self.driver.implicitly_wait(10)
            print("WebDriver initialized")  # 添加调试信息
        except Exception as e:
            print(f"Error initializing WebDriver: {e}")

        site_name = 'site1'
        self.logger = logging.getLogger(site_name)
        handler = logging.FileHandler(f'logs/{site_name}.log')
        handler.setFormatter(logging.Formatter('%(asctime)s - %(levelname)s - %(message)s'))
        self.logger.addHandler(handler)
        self.logger.setLevel(logging.INFO)
        print("Initialized Site1Crawler")  # 添加调试信息

    def crawl(self):
        print(f"Starting crawl for {self.url}")  # 添加调试信息
        self.logger.info(f"Starting crawl for {self.url}")  # 添加调试信息
        try:
            self.driver.get(self.url)
            self.logger.info(f"Accessed URL: {self.url}")  # 添加调试信息
            time.sleep(2)
            # 设置最长等待时间为10s
            self.driver.implicitly_wait(10)

            # 爬取逻辑
            div_element = self.driver.find_element(By.CSS_SELECTOR, 'tbody')
            list_articles = div_element.find_elements(By.CSS_SELECTOR, 'tr')
            articles_data = []
            for article in list_articles:
                title_element = article.find_element(By.TAG_NAME, "a")
                title_text = title_element.text
                article_link = title_element.get_attribute("href")
                brand_element = article.find_element(By.CSS_SELECTOR, '.views-field.views-field-field-brand')
                brand_text = brand_element.text
                time_element = article.find_element(By.CSS_SELECTOR, '.views-field.views-field-field-date')
                time_text = time_element.text
                parsed_date = datetime.strptime(time_text, '%d %b %Y')
                formatted_date = parsed_date.strftime('%Y-%m-%d')
                articles_data.append((title_text, article_link, brand_text, formatted_date))
            print(f"Collected {len(articles_data)} articles")  # 添加调试信息
            self.logger.info(f"Collected {len(articles_data)} articles")  # 添加调试信息
            self.save_to_db(articles_data)
        except Exception as e:
            self.logger.error(f"Error while crawling {self.url}: {e}")
            print(f"Error while crawling {self.url}: {e}")  # 添加调试信息
        finally:
            self.driver.quit()

    def save_to_db(self, data):
        db: Session = SessionLocal()
        try:
            for item in data:
                title_text, article_link, brand_text, formatted_date = item
                existing_entry = db.query(WebData).filter(WebData.link_url == article_link).first()
                if existing_entry:
                    self.logger.info(f"Article link {article_link} already exists, skipping insertion.")
                    continue
                db_data = WebData(
                    title=title_text,
                    author=brand_text,
                    info_type='T0',
                    post_agency='科技部门',
                    nation='澳大利亚',
                    date=formatted_date,
                    link_url=article_link,
                    domain='F0',
                    subject='非F1领域',
                    text=''
                )
                db.add(db_data)
            db.commit()
            self.logger.info(f"Data saved to database")  # 添加调试信息
        except Exception as e:
            self.logger.error(f"Error while saving data to database: {e}")
            print(f"Error while saving data to database: {e}")  # 添加调试信息
            db.rollback()
        finally:
            db.close()
