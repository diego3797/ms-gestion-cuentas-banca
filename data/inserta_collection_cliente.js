db.client.insertMany([
{
	"clientType" : "Personal",
	"dataPersonal" : {
		"documentType" : "1",
		"documentNumber" : "45859966",
		"name" : "Grabiela",
		"lastFather" : "Serpa",
		"lastMother" : "Gutierres",
		"birthDay" : "1985-10-12"
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
	"product" : [
		{
			"account" : [
				{
					"type" : "Ahorro",
					"card" : "552758596322555",
					"number" : "1992001451155",
					"limitovement" : 3,
					"movements" : [
						{
							"numberOperation" : "12581",
							"dateOperation" : "2022-01-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 100.00
						},
						{
							"numberOperation" : "12582",
							"dateOperation" : "2022-12-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 1500.00
						},
						{
							"numberOperation" : "12583",
							"dateOperation" : "2023-01-01 19:22:05",
							"operationType" : "RETIRO",
							"mount" : 200.00
						}
					],
					"dateCreation" : "2021-11-15 18:35:25",
					"sucursal" : {
						"id" : "144",
						"name" : "ucrcal Las Gardenias",
						"address" : "Cale Las Gardenias 65",
						"nameOperator" : "Patricio Franco Mena",
						"nameManager" : "Susana Rios Torres"
					},
					"balance" : 1000.00
				},
				{
					"type" : "Cta Corriente",
					"card" : "4552758596322556",
					"number" : "1992001451156",
					"comission" : 0.75,
					"movements" : [
						{
							"numberOperation" : "19685",
							"dateOperation" : "2023-01-05 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 800.00
						},
						{
							"numberOperation" : "19686",
							"dateOperation" : "2023-01-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 400.00
						},
						{
							"numberOperation" : "19687",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "RETIRO",
							"mount" : 200.00
						}
					],
					"dateCreation" : "2022-12-15 18:35:25",
					"sucursal" : {
						"id" : "144",
						"name" : "cal Las Gardenias",
						"address" : "Cale Las Gardenias 65",
						"nameOperator" : "Alejandro Brando Perez",
						"nameManager" : "Carlos Barreda Torres"
					},
					"balance" : 1200.00
				}
			],
			"credit" : [
				{
					"type" : "Personal",
					"card" : "3777548264544",
					"number" : "15484554144",
					"limitCredit" : 5000.00,
					"movements" : [
						{
							"numberOperation" : "23658",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "RETIRO",
							"mount" : 1000.00
						}
					],
					"dateCreation" : "2023-02-15 18:35:25",
					"sucursal" : {
						"id" : "144",
						"name" : "ucrcal Las Camelias",
						"address" : "Cale Las Camelias 65",
						"nameOperator" : "Patricio Franco Mena",
						"nameManager" : "Susana Rios Torres"
					},
					"balance" : 4000.00
				}
			]
		}
	]
},
{
	"clientType" : "Personal",
	"dataPersonal" : {
		"documentType" : "1",
		"documentNumber" : "45859966",
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
	"product" : [
		{
			"account" : [
				{
					"type" : "Plazo Fijo",
					"card" : "552758596322542",
					"number" : "1992001451142",
					"dayMovement" : 12,
					"movements" : [
						{
							"numberOperation" : "19515",
							"dateOperation" : "2022-01-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 2000.00
						},
						{
							"numberOperation" : "19516",
							"dateOperation" : "2022-12-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 500.00
						},
						{
							"numberOperation" : "19517",
							"dateOperation" : "2023-01-01 19:22:05",
							"operationType" : "RETIRO",
							"mount" : 100.00
						}
					],
					"dateCreation" : "2024-01-15 18:35:25",
					"sucursal" : {
						"_id" : "144",
						"name" : "ucrcal Las Gardenias 66",
						"address" : "Cale Las Gardenias 66",
						"nameOperator" : "Patricio Franco Mena",
						"nameManager" : "Susana Rios Torres"
					},
					"balance" : 2500.00
				},
				{
					"type" : "Ahorro",
					"card" : "4552758596322543",
					"number" : "1992001451143",
					"comission" : 0,
					"movements" : [
						{
							"numberOperation" : "11502",
							"dateOperation" : "2023-01-05 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 800.00
						},
						{
							"numberOperation" : "11503",
							"dateOperation" : "2023-01-10 10:05:45",
							"operationType" : "DEPOSITO",
							"mount" : 400.00
						},
						{
							"numberOperation" : "11504",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "DEPOSITO",
							"mount" : 200.00
						}
					],
					"dateCreation" : "2022-12-15 18:35:25",
					"sucursal" : {
						"_id" : "144",
						"name" : "cal Las Gardenias",
						"address" : "Cale Las Gardenias 77",
						"nameOperator" : "Alejandro Brando Perez",
						"nameManager" : "Carlos Barreda Torres"
					},
					"balance" : 1400.00
				}
			],
			"credit" : [
				{
					"type" : "Personal",
					"number" : "15484554177",
					"limitCredit" : 2000.00,
					"movements" : [
						{
							"numberOperation" : "13252",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 300.00
						},
						{
							"numberOperation" : "13253",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 700.00
						}
					],
					"dateCreation" : "2023-02-15 18:35:25",
					"sucursal" : {
						"_id" : "144",
						"name" : "ucrcal Las Camelias",
						"address" : "Cale Las Camelias 77",
						"nameOperator" : "Patricio Franco Mena",
						"nameManager" : "Susana Rios Torres"
					},
					"balance" : 1000.00
				}
			]
		}
	]
},
{
	"clientType" : "Empresarial",
	"dataCompany" : {
		"ruc" : "10586325452",
		"name" : "Compañia ACME 01",
		"dateCreationCompany" : "2016-08-15"
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
	"product" : [
		{
			"credit" : [
				{
					"type" : "Empresarial",
					"card" : "3777548264581",
					"number" : "15484554181",
					"limitCredit" : 25000.00,
					"movements" : [
						{
							"numberOperation" : "17556",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 300.00
						},
						{
							"numberOperation" : "17557",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 1000.00
						},
						{
							"numberOperation" : "17558",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 500.00
						},
						{
							"numberOperation" : "17559",
							"dateOperation" : "2023-01-15 19:22:05",
							"operationType" : "CONSUMO",
							"mount" : 750.00
						}
					],
					"company" : {
						"titular" : [
							{
								"documentType" : "1",
								"documentNumber" : "45859966",
								"name" : "Alexandra",
								"lastFather" : "Merino",
								"lastMother" : "Alvarez",
								"birthdate" : "1985-05-25"
							},
							{
								"documentType" : "1",
								"documentNumber" : "45859945",
								"name" : "Pedro",
								"lastFather" : "Veltran",
								"lastMother" : "Torres",
								"birthdate" : "1985-05-25"
							}
						],
						"autorizedSignatory" : [
							{
								"documentType" : "1",
								"documentNumber" : "45859966",
								"name" : "Alexandra",
								"lastFather" : "Merino",
								"lastMother" : "Alvarez",
								"birthdate" : "1985-05-25"
							}
						]
					},
					"dateCreation" : "2023-02-15 18:35:25",
					"sucursal" : {
						"_id" : "144",
						"name" : "ucrcal Las Camelias",
						"address" : "Cale Las Camelias 77",
						"nameOperator" : "Patricio Franco Mena",
						"nameManager" : "Susana Rios Torres"
					},
					"balance" : 22450.00
				}
			]
		}
	]
}    
])