%dw 2.0
output application/json
var emailSubject = "Murphy Oil Corp - Reserves Report - " ++ vars.countryFieldArr[0] ++ " - " ++ vars.countryFieldArr[1]
var emailBlurb = "Reserves Report for " ++ vars.countryFieldArr[0] ++ " - " ++ vars.countryFieldArr[1]
---
{
 	"documents":[
 		{
 			"documentId":"1",
 			"name":vars."report-name"
 		}
 	],
	"emailSubject":emailSubject,
	"emailBlurb":emailBlurb,
	"status":"sent",
	"recipients":{
   		"signers": vars.combineArrJson.signers
   	}
}