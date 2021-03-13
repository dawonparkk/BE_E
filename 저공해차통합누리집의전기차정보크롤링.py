import urllib.request 
import bs4
import pandas as pd
import re
url="https://www.ev.or.kr/portal/carInfoView?pMENUMST_ID=&cartype=1C"
html= urllib.request.urlopen(url)
bs_obj=bs4.BeautifulSoup(html,"html.parser")
maindiv = bs_obj.find("div", {"class":"itemCont"})
names =maindiv.findAll("h4")
infos =maindiv.findAll("dd")

# cols=[]
# for name in names:
#     ptag = name.find("p")
#     span = name.find ("span")
#     cols.append(ptag.text+span.text)
# cols
# df=pd.DataFrame(cols,columns=['전기차종'])
# df.to_excel('C:\빅데이터이해와활용\KoNLP\전기차종.xlsx',index=False)    
    
lst=[]
for info in infos:
    span = info.find ("span")
    pattern='[\n|\r|\t]'
    pattern2=re.compile(r'\s+')
    texts=re.sub(pattern=pattern,repl=' ',string=info.text)
    textss=re.sub(pattern=pattern2,repl='',string=texts)
    lst.append(textss)

df=pd.DataFrame(lst,columns=['정보'])
df.to_excel('C:\빅데이터이해와활용\KoNLP\전기차종별정보.xlsx',index=False)

#prod.손향지
