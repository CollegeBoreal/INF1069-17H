To retrieve a currency pair, for example the Bitcoin against the US Dollar, we use read.csv against data provided by quandl

```
>  bitstampusd <- read.csv('http://www.quandl.com/api/v1/datasets/BCHARTS/BITSTAMPUSD.csv?&trim_start=2013-09-01&trim_end=2015-01-10&sort_order=asc', colClasses=c('Date'='Date'))
```

Let's plot some data

```
> plot(bitstampusd$Date,bitstampusd$Close)
```

Rplot

Let's use some Continuous Compounded Return data by adding parameter retclass="ts" (Zoo class might be better)

```
> btc.ts <- as.ts(bitstampusd$Close)
```

To Create the $latex f(x) $ function of returns, I use the diff function to calculate $latex log\left(\frac{Price(t)}{Price(t-1)}\right)$ from $latex rt = log ( 1 + R) = log\left(\frac{Price(t)}{Price(t-1)}\right) $

```
> btc.pdf <- log(lag(btc.ts))-log(btc.ts)
```

or 

```
> btc.pdf <- diff(log(btc.ts))
```

Rplot01

Some Descriptive Statistics:

The mean $latex \mu $

```
> mean(btc.pdf)
[1] 0.006749139
```

The Variance $latex \sigma^2 $

```
> var(btc.pdf)
[1] 0.001528914
```

As you can see a +.15% average return is not that interesting but again I believe Bitcoin Virtual Currency is still the way to go.

The Standard Deviation $latex \sigma $

```
> sd(btc.pdf)
[1] 0.05436408
```

Same for the standard deviation, 5% may look risky for an investor.

```
> library(PerformanceAnalytics)
> skewness(btc.pdf)
[1] 0.00998609

> kurtosis(btc.pdf)
[1] 7.774766
```

Finally some Bell Curve Equation $latex f(x,\mu ,\sigma^2) = \frac{1}{\sigma \sqrt{2\pi}}e^{-\frac{1}{2}(\frac{x-\mu}{\sigma})^2} $

Showing that Bitcoin still has fat tails a good candidate for Black Swan Theory.

```
> chart.Histogram(btc.pdf)
```

Rplot02
