from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

options = Options()
prefs = {"profile.managed_default_content_settings.images": 2}
options.add_experimental_option("prefs", prefs)
options.add_argument('--start-maximized')
chromedriver_path = 'D:/CSsoftware/anaconda3/envs/webcrawler/Scripts/chromedriver.exe'
service = Service(chromedriver_path)
driver = webdriver.Chrome(service=service,options=options)
driver.get('https://www.baidu.com')
print(driver.title)
driver.quit()
