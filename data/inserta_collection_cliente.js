db.client.insertMany([
{
	"clientType" : "Personal",
	"profileType" : "Normal",
	"dataPersonal" : {
		"documentType" : "1",
		"documentNumber" : "45859966",
		"name" : "Grabiela",
		"lastFather" : "Serpa",
		"lastMother" : "Gutierres",
		"birthDay" :  ISODate("1985-10-12T00:00:00")
	},
	"email" : "grabiela.serpa@gmail.com",
	"phono" : "56885231",
	"address" : {
		"street" : "Lytton Ave",
		"number" : "145",
		"district" : "San Isidro",
		"province" : "Lima",
		"state" : "Lima"
	},
	"product" : {
	    "account" : [
        	{
        		"type" : "Ahorro",
        		"card" : "552758596322555",
        		"number" : "1992001451155",
        		"movements" : [
        			{
        				"numberOperation" : "12581",
        				"dateOperation" : ISODate("2022-01-20T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("100.00")
        			},
        			{
        				"numberOperation" : "12582",
        				"dateOperation" : ISODate("2022-12-10T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("1500.00")
        			},
        			{
        				"numberOperation" : "12583",
        				"dateOperation" : ISODate("2023-01-01T19:22:05"),
        				"operationType" : "RETIRO",
        				"amount" : NumberDecimal("-200.00")
        			}
        		],
        		"dateCreation" : ISODate("2021-11-15T18:35:25"),
        		"sucursal" : {
        			"id" : "144",
        			"name" : "ucrcal Las Gardenias",
        			"address" : "Cale Las Gardenias 65",
        			"nameOperator" : "Patricio Franco Mena",
        			"nameManager" : "Susana Rios Torres"
        		},
        		"balance" : NumberDecimal("1600.00")
        	},
        	{
        		"type" : "Cta Corriente",
        		"card" : "4552758596322556",
        		"number" : "1992001451156",
        		"movements" : [
        			{
        				"numberOperation" : "19685",
        				"dateOperation" : ISODate("2023-01-05T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("800.00")
        			},
        			{
        				"numberOperation" : "19686",
        				"dateOperation" : ISODate("2023-01-10T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("400.00")
        			},
        			{
        				"numberOperation" : "19687",
        				"dateOperation" : ISODate("2023-01-15T19:22:05"),
        				"operationType" : "RETIRO",
        				"amount" : NumberDecimal("-200.00")
        			}
        		],
        		"dateCreation" : ISODate("2022-12-15T18:35:25"),
        		"sucursal" : {
        			"id" : "144",
        			"name" : "cal Las Gardenias",
        			"address" : "Cale Las Gardenias 65",
        			"nameOperator" : "Alejandro Brando Perez",
        			"nameManager" : "Carlos Barreda Torres"
        		},
        		"balance" : NumberDecimal("1200.00")
        	}
        ],
        "credit" : [
        	{
        		"type" : "Personal",
        		"card" : "3777548264544",
        		"number" : "15484554144",
        		"limitCredit" : NumberDecimal("5000.00"),
        		"movements" : [
        			{
        				"numberOperation" : "23658",
        				"dateOperation" : ISODate("2023-01-15T19:22:05"),
        				"operationType" : "CONSUMO",
        				"amount" : NumberDecimal("-1000.00")
        			}
        		],
        		"dateCreation" : ISODate("2023-02-15T18:35:25"),
        		"sucursal" : {
        			"id" : "144",
        			"name" : "ucrcal Las Camelias",
        			"address" : "Cale Las Camelias 65",
        			"nameOperator" : "Patricio Franco Mena",
        			"nameManager" : "Susana Rios Torres"
        		},
        		"balance" : NumberDecimal("4000.00")
        	}
        ]
	}
},
{
	"clientType" : "Personal",
	"profileType" : "VIP",
	"dataPersonal" : {
		"documentType" : "1",
		"documentNumber" : "45859977",
		"name" : "Alexandra Paola",
		"lastFather" : "Merino",
		"lastMother" : "Alvarez"
	},
	"email" : "alexandra.merino@gmail.com",
	"phono" : "968524201",
	"address" : {
		"street" : "Lytton Ave 2",
		"number" : "145",
		"district" : "San Isidro 2",
		"province" : "Lima",
		"state" : "Lima"
	},
	"product" : {
	    "account" : [
        	{
        		"type" : "Plazo Fijo",
        		"card" : "552758596322542",
        		"number" : "1992001451142",
        		"dayMovement" : 12,
        		"movements" : [
        			{
        				"numberOperation" : "19515",
        				"dateOperation" : ISODate("2022-01-10T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("2000.00")
        			},
        			{
        				"numberOperation" : "19516",
        				"dateOperation" : ISODate("2022-12-10T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("500.00")
        			},
        			{
        				"numberOperation" : "19517",
        				"dateOperation" : ISODate("2023-01-01T19:22:05"),
        				"operationType" : "RETIRO",
        				"amount" : NumberDecimal("-100.00")
        			}
        		],
        		"dateCreation" : ISODate("2024-01-15T18:35:25"),
        		"sucursal" : {
        			"_id" : "144",
        			"name" : "ucrcal Las Gardenias 66",
        			"address" : "Cale Las Gardenias 66",
        			"nameOperator" : "Patricio Franco Mena",
        			"nameManager" : "Susana Rios Torres"
        		},
        		"balance" : NumberDecimal("2500.00")
        	},
        	{
        		"type" : "Ahorro",
        		"card" : "4552758596322543",
        		"number" : "1992001451143",
        		"movements" : [
        			{
        				"numberOperation" : "11502",
        				"dateOperation" : ISODate("2023-01-05T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("800.00")
        			},
        			{
        				"numberOperation" : "11503",
        				"dateOperation" : ISODate("2023-01-10T10:05:45"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("400.00")
        			},
        			{
        				"numberOperation" : "11504",
        				"dateOperation" : ISODate("2023-01-15T19:22:05"),
        				"operationType" : "DEPOSITO",
        				"amount" : NumberDecimal("200.00")
        			}
        		],
        		"dateCreation" : ISODate("2022-12-15T18:35:25"),
        		"sucursal" : {
        			"_id" : "144",
        			"name" : "cal Las Gardenias",
        			"address" : "Cale Las Gardenias 77",
        			"nameOperator" : "Alejandro Brando Perez",
        			"nameManager" : "Carlos Barreda Torres"
        		},
        		"balance" : NumberDecimal("1400.00")
        	}
        ],
        "credit" : [
        	{
        		"type" : "Personal",
        		"number" : "15484554177",
        		"limitCredit" : NumberDecimal("2000.00"),
        		"movements" : [
        			{
        				"numberOperation" : "13252",
        				"dateOperation" : ISODate("2023-01-15T19:22:05"),
        				"operationType" : "CONSUMO",
        				"amount" : NumberDecimal("-300.00")
        			},
        			{
        				"numberOperation" : "13253",
        				"dateOperation" : ISODate("2023-01-15T19:22:05"),
        				"operationType" : "CONSUMO",
        				"amount" : NumberDecimal("-700.00")
        			}
        		],
        		"dateCreation" : ISODate("2023-02-15T18:35:25"),
        		"sucursal" : {
        			"_id" : "144",
        			"name" : "ucrcal Las Camelias",
        			"address" : "Cale Las Camelias 77",
        			"nameOperator" : "Patricio Franco Mena",
        			"nameManager" : "Susana Rios Torres"
        		},
        		"balance" : NumberDecimal("1000.00")
        	}
        ]
	}
},
{
	"clientType" : "Empresarial",
	"profileType" : "PYME",
	"dataCompany" : {
		"ruc" : "10586325452",
		"name" : "Compañia ACME 01",
		"dateCreationCompany" : ISODate("2016-08-15T00:0000")
	},
	"email" : "compania.acme01@gmail.com",
	"phono" : "968524201",
	"address" : {
		"street" : "Lytton Ave 2",
		"number" : "145",
		"district" : "San Isidro 2",
		"province" : "Lima",
		"state" : "Lima"
	},
	"product" : {
		"credit" : [
			{
				"type" : "Empresarial",
				"card" : "3777548264581",
				"number" : "15484554181",
				"limitCredit" : NumberDecimal("25000.00"),
				"movements" : [
					{
						"numberOperation" : "17556",
						"dateOperation" : ISODate("2023-01-15T19:22:05"),
						"operationType" : "CONSUMO",
						"amount" : NumberDecimal("-300.00")
					},
					{
						"numberOperation" : "17557",
						"dateOperation" : ISODate("2023-01-15T19:22:05"),
						"operationType" : "CONSUMO",
						"amount" : NumberDecimal("-1000.00")
					},
					{
						"numberOperation" : "17558",
						"dateOperation" : ISODate("2023-01-15T19:22:05"),
						"operationType" : "CONSUMO",
						"amount" : NumberDecimal("-500.00")
					},
					{
						"numberOperation" : "17559",
						"dateOperation" : ISODate("2023-01-15T19:22:05"),
						"operationType" : "CONSUMO",
						"amount" : NumberDecimal("-750.00")
					}
				],
				"company" : {
					"titular" : [
						{
							"documentType" : "1",
							"documentNumber" : "45859988",
							"name" : "Alexandra",
							"lastFather" : "Merino",
							"lastMother" : "Alvarez",
							"birthdate" : ISODate("1985-05-25T00:00:00")
						},
						{
							"documentType" : "1",
							"documentNumber" : "45859945",
							"name" : "Pedro",
							"lastFather" : "Veltran",
							"lastMother" : "Torres",
							"birthdate" : ISODate("1985-05-25T00:00:00")
						}
					],
					"autorizedSignatory" : [
						{
							"documentType" : "1",
							"documentNumber" : "45859999",
							"name" : "Alexandra",
							"lastFather" : "Merino",
							"lastMother" : "Alvarez",
							"birthdate" : ISODate("1985-05-25T00:00:00")
						}
					]
				},
				"dateCreation" : ISODate("2023-02-15T18:35:25"),
				"sucursal" : {
					"_id" : "144",
					"name" : "ucrcal Las Camelias",
					"address" : "Cale Las Camelias 77",
					"nameOperator" : "Patricio Franco Mena",
					"nameManager" : "Susana Rios Torres"
				},
				"balance" : NumberDecimal("22450.00")
			}
		]
	}

}
])