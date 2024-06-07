from crawlers.site1_crawler import Site1Crawler #测试crawlers目录下的爬虫模块
from db import SessionLocal, engine
from models import WebData
import sys
import os

# 将项目根目录添加到sys.path
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

def test_site1_crawler():
    # 配置测试URL（确保该URL有效且适合测试）
    config = {
        'url': 'https://www.industry.gov.au/publications?pub-topic=2906&pub-category=All&publisher=All&field_date_value=All'
    }

    # 创建数据库会话
    db = SessionLocal()

    try:
        # 初始化并运行爬虫
        crawler = Site1Crawler(config)
        crawler.crawl()

        # 检查是否有数据插入到数据库中
        result = db.query(WebData).first()
        if result:
            print("Data inserted successfully!")
            print(f"Title: {result.title}, URL: {result.link_url}")
        else:
            print("No data found in database.")
    except Exception as e:
        print(f"Error during test: {e}")
    finally:
        db.close()


if __name__ == "__main__":
    test_site1_crawler()
