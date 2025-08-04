package com.came.parkare.dashboardapp.infrastructure.source.mocks

object ElementListMock {
    fun getElementList(): String {
        return """
            [
                {
                    "element-type":"box",
                    "data": {
                        "backgroundColor": "#FFFFFF",
                        "density": 0,
                        "roundBorder": 0,
                        "hasShadow": false,
                        "padding": 0,
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "width": null,
                        "height": null,
                        "margin": null,
                        "content": []
                    }
                },
                {
                    "element-type":"column",
                    "data": {
                        "backgroundColor": "#FFFFFF",
                        "density": 0,
                        "roundBorder": 0,
                        "hasShadow": false,
                        "spacing": 0,
                        "padding": 0,
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "content": []
                    }
                },
                {
                    "element-type":"row",
                    "data": {
                        "backgroundColor": "#FFFFFF",
                        "density": 0,
                        "roundBorder": 0,
                        "hasShadow": false,
                        "spacing": 0,
                        "padding": 0,
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "content": []
                    }        
                },
                {
                    "element-type":"text",
                    "data": {
                        "dashboardItemId": "default_id",
                        "defaultText": "",
                        "textSize": 0,
                        "textColor": "#000000",
                        "padding": 0,
                        "fontWeight": "medium",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "translations": null
                    }        
                },
                {
                    "element-type":"image",
                    "data": {
                        "dashboardItemId": "default_image_id",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "fileName": null,
                        "localFilePath": null,
                        "intervalTime": null,
                        "height": 60,
                        "width": 60
                    }
                },
                {
                    "element-type":"spacer",
                    "data": {
                        "value": 0 
                    }
                },
                {
                    "element-type":"video",
                    "data": {
                        "dashboardItemId": "default_video_id",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "fileName": null,
                        "localFilePath": null,
                        "height": 60,
                        "width": 60
                    }
                }
            ]
        """.trimIndent()
    }

    fun getDefaultElements(): String{
        return """
            [
              {
                "element-type": "text",
                "data": {
                  "dashboardItemId": "parking-name",
                  "defaultText": "SABA PAU CLARIS",
                  "textSize": 36,
                  "textColor": "#222c32",
                  "padding": 4,
                  "fontWeight": "Medium",
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "translations": {
                    "lang1": "SABA PAU CLARIS"
                  }
                }
              },
              {
                "element-type": "spacer",
                "data": {
                  "value": 15
                }
              },
              {
                "element-type": "text",
                "data": {
                 "dashboardItemId": "license-plate-value",
                 "defaultText": "---- ---",
                 "textSize": 72,
                 "textColor": "#000000",
                 "padding": 4,
                 "fontWeight": "Bold",
                 "dataKey": "MainLicensePlate",
                 "ditTypeCode": 10,
                 "validValue": null,
                 "translations": null
                }
               },
              {
                "element-type": "box",
                "data": {
                  "backgroundColor": "#FF5800",
                  "density": 100,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "width": null,
                  "height": null,
                  "margin": null,
                  "padding": 12,
                  "content": [
                    {
                      "element-type": "text",
                      "data": {
                        "dashboardItemId": "cannot-read",
                        "defaultText": "IMPORT PENDENT\n\rDE PAGAMENT",
                        "textSize": 28,
                        "textColor": "#FFFFFF",
                        "padding": 12,
                        "fontWeight": "Bold",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "translations": {
                          "lang1": "IMPORT PENDENT\n\rDE PAGAMENT"
                        }
                      }
                    }
                  ]
                }
              },
              {
                "element-type": "box",
                "data": {
                  "backgroundColor": "#FFFFFF",
                  "density": 0,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "width": null,
                  "height": null,
                  "margin": null,
                  "padding": 12,
                  "content": [
                    {
                      "element-type": "image",
                      "data": {
                        "dashboardItemId": "ico-disabled",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "fileName": "ico-disabled",
                        "localFilePath": null,
                        "intervalTime": null,
                        "height": 480,
                        "width": 480
                      }
                    }
                  ]
                }
              },
              {
                "element-type": "column",
                "data": {
                  "backgroundColor": "#4c9f14",
                  "density": 100,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "spacing": 5,
                  "padding": 4,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "content": [
                    {
                      "element-type": "text",
                      "data": {
                        "dashboardItemId": "can-pass",
                        "defaultText": "POTS PASSAR",
                        "textSize": 28,
                        "textColor": "#FFFFFF",
                        "padding": 4,
                        "fontWeight": "Bold",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "translations": {
                          "lang1": "POTS PASSAR"
                        }
                      }
                    }
                  ]
                }
              },
              {
                "element-type": "column",
                "data": {
                  "backgroundColor": "#222c32",
                  "density": 100,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "spacing": 5,
                  "padding": 4,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "content": [
                    {
                      "element-type": "text",
                      "data": {
                        "dashboardItemId": "ticket-less-label",
                        "defaultText": "SORTIDA SENSE TICKET",
                        "textSize": 32,
                        "textColor": "#FFFFFF",
                        "padding": 4,
                        "fontWeight": "Bold",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "translations": {
                          "lang1": "SORTIDA SENSE TICKET"
                        }
                      }
                    }
                  ]
                }
              },
              {
                "element-type": "row",
                "data": {
                  "backgroundColor": "#FFFFFF",
                  "density": 0,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "spacing": 8,
                  "padding": 4,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "content": [
                    {
                      "element-type": "text",
                      "data": {
                        "dashboardItemId": "welcome-label",
                        "defaultText": "Gràcies per\n\rla seva visita",
                        "textSize": 24,
                        "textColor": "#222c32",
                        "padding": 4,
                        "fontWeight": "Regular",
                        "dataKey": null,
                        "ditTypeCode": null,
                        "validValue": null,
                        "translations": {
                          "lang1": "Gràcies per\n\rla seva visita"
                        }
                      }
                    }
                  ]
                }
              },
              {
                "element-type": "row",
                "data": {
                  "backgroundColor": "#222c32",
                  "density": 100,
                  "roundBorder": 0,
                  "hasShadow": false,
                  "spacing": 12,
                  "padding": 8,
                  "dataKey": null,
                  "ditTypeCode": null,
                  "validValue": null,
                  "content": [
                    {
                      "element-type": "text",
                      "data": {
                        "dashboardItemId": "entry-type-value",
                        "defaultText": "-------",
                        "textSize": 36,
                        "textColor": "#00a599",
                        "padding": 0,
                        "fontWeight": "Bold",
                        "dataKey": "CardClass",
                        "ditTypeCode": 9,
                        "validValue": 0,
                        "translations": {
                          "lang1": "Tiquet / Tique"
                        }
                      }
                    }
                  ]
                }
              }
            ]
        """.trimIndent()
    }
}