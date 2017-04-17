#	Comprendre et analyser les données : Boston housing data  


•	https://archive.ics.uci.edu/ml/machine-learning-databases/housing/  
•	Descriptions des variables  


1. CRIM: per capita crime rate by town 
2. ZN: proportion of residential land zoned for lots over 25,000 sq.ft. 
3. INDUS: proportion of non-retail business acres per town 
4. CHAS: Charles River dummy variable (= 1 if tract bounds river; 0 otherwise) 
5. NOX: nitric oxides concentration (parts per 10 million) 
6. RM: average number of rooms per dwelling 
7. AGE: proportion of owner-occupied units built prior to 1940 
8. DIS: weighted distances to five Boston employment centres 
9. RAD: index of accessibility to radial highways 
10. TAX: full-value property-tax rate per $10,000 
11. PTRATIO: pupil-teacher ratio by town 
12. B: 1000(Bk - 0.63)^2 where Bk is the proportion of blacks by town 
13. LSTAT: % lower status of the population 
14. MEDV: Median value of owner-occupied homes in $1000's

## R Code

```
> housing<-read.fwf(
        file=url('https://archive.ics.uci.edu/ml/machine-learning-databases/housing/housing.data'),
       widths=c(8,7,8,3,8,8,7,8,4,7,7,7,7,7),
      col.names=c("CRIM","ZN","INDUS","CHAS","NOX","RM","AGE","DIS","RAD","TAX","PTRATIO","B","LSTAT","MEDV")
 )
> fix (housing)
> str(housing)
> summary(housing)
> boxplot(housing$AGE)
> with(housing, boxplot(AGE))
> with(housing, boxplot(CRIM,INDUS))
> with(housing, plot(AGE, TAX, xlab="AGE",ylab="TAX"))
> with (housing, hist(RM)) loi normale
> with (housing, hist(AGE)) plus de maisons agées
> with(housing, hist(DIS)) loi lognormale
> with(housing, hist(RAD)) TAX et RAD se ressemblent beaucoup
> with(housing, hist(LSTAT))la loi lognormale 
> pairs(housing[,c("RM","LSTAT","MEDV")])
```

## Test de normalité
```
> result <- with(housing, shapiro.test(RM))
> print (result)
```

## Régression linéaire
```
> mod1<- lm(MEDV ~RM, data = housing)
> summary(mod1)
> newdata1<-data.frame(RM=7)
> predicted.values<-predict(mod1,newdata1)
> print(predicted.values)
```

```
> mod2<- lm(MEDV ~RM+AGE, data = housing)
> summary(mod2)
> newdata2<-data.frame(RM=7, AGE=60)
> predicted.values<-predict(mod2,newdata2)
> print(predicted.values)
```

