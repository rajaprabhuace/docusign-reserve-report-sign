%dw 2.0
output application/json
var arr = vars.commentRoleArr
---
{
	"signers": arr map(item, itemIndex) -> {
		"tabs" : {
			"textTabs" : [
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt1'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt1') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 0),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt2'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt2') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 1),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt3'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt3') ++ (item splitBy("|"))[2]					
				}) if (itemIndex == 2),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt4'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt4') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 3),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt5'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt5') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 3),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt6'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt6') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 3),
				({
					"recipientId" : (item splitBy("|"))[2],
					"anchorString" : p('docusign.anchorstring.texttab.txt7'),
					"width" : p('docusign.anchorstring.texttab.width'),
					"height" : p('docusign.anchorstring.texttab.height'),
					"required" : "false",
					"shared" : "true",
					"anchorYOffset" : p('docusign.anchorstring.texttab.yoffsets'),
					"anchorXOffset" : p('docusign.anchorstring.texttab.xoffsets'),
					"tabLabel" : p('docusign.anchorstring.texttab.txt7') ++ (item splitBy("|"))[2]
				}) if (itemIndex == 3)
			]
		},
		"email" : (item splitBy("|"))[0],
		"name" : (item splitBy("|"))[1],
		"recipientId" : (item splitBy("|"))[2],
		"routingOrder" : (item splitBy("|"))[2]		
	}
}