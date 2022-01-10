#!/usr/bin/env python
# coding: utf-8

# In[3]:


import folium
import pandas as pd

df = pd.read_csv('C:/빅데이터이해와활용/충전소리스트22.csv')
df = df[['충전소', 'Y', 'X']]

trip = pd.read_csv('C:/빅데이터이해와활용/전국관광정보들.csv')
trip = trip[['명칭', '위도', '경도']]

center = [37.541, 126.986] 
m = folium.Map(location=center, zoom_start=15) 
# 1000 개의 데이터만 그려냅니다. 
for i in range(len(df)): 
    folium.Circle( location = df.loc[i, ['Y', 'X']], 
                  tooltip = df.loc[i, '충전소'],
                  radius=100
                 ).add_to(m)

for i in range(len(trip)): 
    folium.Marker( location = trip.loc[i, ['위도', '경도']], 
                  tooltip = trip.loc[i, '명칭'],
                  icon=folium.Icon(color='red',icon='star')
                 ).add_to(m)
m.save('map.html')
