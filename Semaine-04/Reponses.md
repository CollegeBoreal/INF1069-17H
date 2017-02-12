# Les fonctions d’agrégation - Réponses

__Question 1__ 

Retourner les états et leurs capitales

__Question 2__ 

Retourner les états et leurs capitales seulement pour ceux qui ont une capitale.

__Question 3__ 
```
> db.IndiaStates.aggregate({$match: {"state": /PRADESH/i}}).pretty()
```

__Question 4__
```
> db.IndiaStates.aggregate(
	{$project: {"cities": 1, "_id": 0}},
	{$unwind: "$cities"},
	{$project: {"firstLetter": {$substr: ["$cities", 0, 1]}}},
	{$group: {"_id": {"firstLetter": "$firstLetter"}, "count": {$sum: 1}}},
	{$sort: {"count": -1}},
	{$limit: 3}
).pretty()
```

__Question 5__
```
> db.IndiaStates.aggregate(
	{
		$match: {"info.area": {$exists: true}}
	},
	{
		$group:
		{
			"_id": {"state": "$state"},
			"maxArea": { $max: "$info.area" }			
		}
	},
	{$sort: {"maxArea": -1}},
	{$limit: 1}
).pretty()

> db.IndiaStates.aggregate(
	{
		$match: { "info.area": { $exists: true}}
	},
	{	$group:
		{
			"_id": null,
			"maxArea": { $max: "$info.area" }			
		} 
	}
).pretty()
```

__Question 6__
```
> db.cityInspections.aggregate(
[    
    {
        $project:
        {   
            "issue_date": "$issue_date",
            "issue_year": {$year: "$issue_date"},
            "issue_month": {$month: "$issue_date"}
        }
    },
    {
        $match: 
        {
            "issue_year": 2015
        }
    },
    {
        $group: 
        {       
            "_id": {"issue_month": "$issue_month"}, "count": {$sum: 1}
        }
    }, 
    {
        $sort: {"_id.issue_month": -1}
    }
]).pretty()
     
>  	db.cityInspections.aggregate(
[    
	{
		$match: 
		{
				$and: 
				[
					{"issue_date": {"$exists": true}},  
					{"issue_date": {$gte: ISODate('2015-01-01'), $lt: ISODate('2016-01-01')}}
				]
		}
	},
	{
		$project:
		{   
			"issue_month": {$month: "$issue_date"}
		}
	},

	{
		$group: 
		{       
			"_id": {"issue_month": "$issue_month"}, "count": {$sum: 1}
		}
	}, 
	{
		$sort: {"_id.issue_month": -1}
	}
]).pretty()
	 
```

__Question 7__
```
> db.cityInspections.aggregate(
[    
    {
        $match: 
        {
            $and: 
			[
                {"result": {"$exists": true}},
                {"result": "Pass"},
                {"issue_date": {"$exists": true}},
                {"issue_date": {$gte: ISODate('2015-01-01'), $lt: ISODate('2016-01-01')}}
            ]
        }
    },
    {
        $project:
        {   
            "issue_month": {$month: "$issue_date"}
        }
    },
    {
        $group: 
        {       
            "_id": {"issue_month": "$issue_month"}, "count": {$sum: 1}
        }
    }, 
    {
        $sort: {"count": -1}
    }
]).pretty()
```

__Question 8__
```
> db.cityInspections.aggregate(
[
	{
		$match: 
		{
            $and: 
			[
                {"address.city": {"$exists": true}},
                {"address.city": {$ne: ""}}
            ]
		}
	},
    {
        $project:
        {
            "city": {$toLower: "$address.city"}
        }
    },
    {
        $group: 
        {       
            "_id": {"city": "$city"}, "count": {$sum: 1}
        }
    },
    {
        $sort: {"count": -1}
    }
]).pretty()
```

__Question 9__

```
var total = db.cityInspections.count();
db.cityInspections.aggregate(
[    
    {
        $group: 
        {       
            "_id": "$result", "count": {$sum: 1}
        }
    },
    {
        $project:
        {
            "result": "$result",
            "count": "$count",
            "percentage": {"$multiply": [{"$divide": [100,total]}, "$count"]},
            "percentageStr": 
            { 
                "$concat": 
                [ 
                    {"$substr": 
                        [ 
                            {"$multiply": 
								[
                                    {"$divide": ["$count", {"$literal": total }]}, 100 
                                ]
                            }, 0, 4 
                        ]
                    }, "%"
                ]
            }
        }
    }
]).pretty()
```

__Question 10__
```
> db.cityInspections.aggregate(
[
    {
        $match:
        {
			$and: 
			[
				{"address.city": {"$exists": 1}}, 
				{"address.city": {"$ne": ""}},
				{"sector": {"$exists": 1}},
				{"sector": {"$ne": ""}}
			]
            
        }
    },
    {
        $project:
        {
            "city": {"$toLower": "$address.city"},
            "sector": {"$toLower": "$sector"}
        }
    },
    {
        $group: 
        {       
            "_id": {"city": "$city", "sector":"$sector"}, "count": {$sum:1}
        }
    },
    {
        $sort: {"_id.city": 1, "_id.sector": 1}
    }
]).pretty()
```


