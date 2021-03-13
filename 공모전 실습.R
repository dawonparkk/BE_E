#쥬피터 터미널에 conda install r-irkernel 입력하면 됨

#1. 전국 전기차 충전소 분포시각화  prod. 손향지 
library(googleVis)
library(dplyr)
db<-read.csv("C:/빅데이터이해와활용/충전소리스트22.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
tip<-gvisMap(db,"XY","충전소",options=list(showTip=T,showLine=T,enableScrollWheel=T,
                            mapType='normal',useMapTypeControl=T,
                            width=800, height=600))
tip$html$header<-gsub("charset=utf-8","charset=euc-kr",tip$html$header)
plot(tip)

#3. 계절 이용 빈도 기각화 - 부산/대구   prod. 손향지 
library(googleVis)
db2<-read.csv("C:/빅데이터이해와활용/2019년 대구지역 충전이력.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
season<-gvisComboChart(db2,options=list(title="대구 충전소별 계절이용 빈도차이", bar="{groupWidth:'100%'}"
				,seriesType="bars",series="{4: {type:'line'}}",width=1500,height=600))
season$html$header<-gsub("charset=utf-8","charset=euc-kr",season$html$header)
plot(season)

db22<-read.csv("C:/빅데이터이해와활용/2019년 부산지역 충전이력.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
season2<-gvisComboChart(db22,,options=list(title="부산 충전소별 계절이용 빈도차이", bar="{groupWidth:'100%'}"
				,seriesType="bars",series="{4: {type:'line'}}",width=1500,height=600))
season2$html$header<-gsub("charset=utf-8","charset=euc-kr",season2$html$header)
plot(season2)

MG<-gvisMerge(season, season2, 
               tableOptions = "cellspacing=\"10\" bgcolor=\"#AABBCC\"",
               horizontal=FALSE)
MG$html$header<-gsub("charset=utf-8","charset=euc-kr",MG$html$header)
plot(MG)

#2. 충전소24시간 기준 이용빈도 시각화 - 대구/부산   prod. 손향지
library(RColorBrewer)
db3<-read.csv("C:/빅데이터이해와활용/대구지역 충전이력.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
rownames(db3)=db3$name
db3<-db3[,2:25]
db3_matrix<-data.matrix(db3)
db_heatmap<-heatmap(db3_matrix,Rowv=NA, Colv=NA, col=brewer.pal(9,"YlOrRd"), scale="column", margin=c(2,4))


db4<-read.csv("C:/빅데이터이해와활용/부산지역 충전이력.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
rownames(db4)=db4$name
db4<-db4[,2:26]
db4_matrix<-data.matrix(db4)
db_heatmap2<-heatmap(db4_matrix,Rowv=NA, Colv=NA, col=brewer.pal(9,"YlOrRd"), scale="column", margin=c(2,4))

#4.
library(googleVis)
dbs<-read.csv("C:/빅데이터이해와활용/전국관광정보들.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
trip<-gvisMap(dbs,"YX","명칭",options=list(showTip=T,showLine=T,enableScrollWheel=T,
                            mapType='normal',useMapTypeControl=T,
                            width=800, height=600))
trip$html$header<-gsub("charset=utf-8","charset=euc-kr",trip$html$header)
plot(trip)

#5 온도차별 주행거리 시각화  prod. 손향지

library(googleVis)
dbdb<-read.csv("C:/빅데이터이해와활용/KoNLP/전기차총주행거리.csv",header=TRUE,sep=",",fileEncoding = "UCS-2LE")
kms<-gvisLineChart(dbdb,options=list(title="전기차종 및 온도차 별 주행거리(단위:km) ", bar="{groupWidth:'100%'}"
				,series="[{lineWidth: 2,targetAxisIndex: 0},
					   {lineWidth: 2, targetAxisIndex:0}, 
                                 {lineWidth: 4,targetAxisIndex:1,lineDashStyle: [10, 2]}]"
				,vAxes="[{title:'1회충전주행거리'},{title:'최고속도출력 km/h',textPosition: 'out'}]"
				,hAxes="[{title:'전기차종',textPosition: 'out'}]"
				,width=2500,height=1000))
kms$html$header<-gsub("charset=utf-8","charset=euc-kr",kms$html$header)
plot(kms)

