from sqlalchemy.orm import Session
from db import SessionLocal
from models import WebData
from datetime import datetime


def test_db_connection():
    # 创建数据库会话
    db: Session = SessionLocal()

    try:
        # 创建一个测试数据
        test_data = WebData(
            id="20240606-0001",
            title="Test Title",
            author="Test Author",
            info_type='T0',
            post_agency='Test Agency',
            nation='Test Nation',
            date=datetime.strptime('2024-06-06', '%Y-%m-%d'),
            link_url="http://example.com",
            domain='F0',
            subject='非F1领域',
            text="Test Text"
        )

        # 将数据添加到会话中
        db.add(test_data)

        # 提交会话
        db.commit()

        print("Data inserted successfully!")
    except Exception as e:
        print(f"Error: {e}")
        db.rollback()
    finally:
        # 关闭会话
        db.close()


if __name__ == "__main__":
    test_db_connection()
