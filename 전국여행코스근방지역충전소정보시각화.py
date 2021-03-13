#!/usr/bin/env python
# coding: utf-8

import folium
from folium import plugins
import pandas as pd
from haversine import haversine
from folium.features import DivIcon

df = pd.read_csv('C:/빅데이터이해와활용/KoNLP/여행코스.csv')
charge = pd.read_csv('C:/빅데이터이해와활용/충전소리스트22.csv')
state = input("원하시는 지역을 입력해주세요.  ex:경상도 ex:강릉시 \n")
category =input("여행카테고리를 입력해주세요 \n ex: 데이트코스 \n 드라이브코스 \n 문화-유적코스 \n 자연-힐링코스 \n 해안코스 \n 레포츠 \n\n")
count=0; order=1

cnt=len(df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])])
cnt2=len(charge[charge['이름'].str.contains(state)==True]) if len(charge[charge['이름'].str.contains(state)==True]) else len(charge[df['광역단체'].str.contains(state)==True])

m=[] distance=[] coordinates=[]
order=1
if cnt: 
    center = df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[0,[2,3]]
    for i in range(cnt):
        m.append(folium.Map(location=center, zoom_start=12)) 
        for j in range(5,df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[i].count(),3):
            coordinates.append( df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[i,[j+1, j+2]])
            folium.Marker( location = df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[i,[j+1, j+2]],
                      #trip.loc[i, ['위도', '경도']], #trip.loc[i, '명칭'],
                      popup = folium.Popup(df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[i,j],
                          max_width=50,show = True),    
                      icon=DivIcon( icon_size=(150,36),icon_anchor=(7,20),
                          html='<div style="font-size: 15pt; color : black">'+str(order)+'</div>',
                        )
                     ).add_to(m[i])
            folium.Circle(df[(df['이름'].isin([str(state)])|df['광역단체'].isin([str(state)]))&df['카테고리'].isin([str(category)])].iloc[i,[j+1, j+2]], radius=100,color = 'red',fill=True).add_to(m[i])

            order+=1
        folium.PolyLine(locations = coordinates,weight=3,color = 'red').add_to(m[i])
        del coordinates
        coordinates=[]
        for k in range(0,cnt2): 
            folium.Circle( location = charge[charge['이름'].str.contains(state)==True].iloc[k,[5,4]]if len(charge[charge['이름'].str.contains(state)==True]) else charge[charge['광역단체'].str.contains(state)==True].iloc[k,[5,4]], 
                  tooltip = charge[charge['이름'].str.contains(state)==True].iloc[k,0]if len(charge[charge['이름'].str.contains(state)==True]) else charge[charge['광역단체'].str.contains(state)==True].iloc[k,0],
                  radius=100
                 ).add_to(m[i])
        m[i].save(state+"_"+category+"_"+str(i)+'.html')
        m[i].save("C:/" + state + "_" + category + "_" + str(i) + ".html")
    print("파일 저장 완료")
    
else:
    print("정확하게 입력하세요")
    
#prod.손향지
