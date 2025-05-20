package com.came.parkare.dashboardapp.infrastructure.source.mocks

object EntryWithTicketTemplate {
    fun get(): String{
        return """
            {
                "templateName":"ENTRY WITH TICKET",
                "screens":[
                    {
                        "dispatch-code": 0,
                        "screen-id": "DLG_BOOT",
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "fileName": "ico-disabled",
                                                "height": 480,
                                                "width": 480
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },        
                    {
                        "dispatch-code": 5,
                        "screen-id": "IDLE",
                        "data": [
                            {
                                "element-type": "row",
                                "data":{

                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Benvingut a",
                                                "textSize": 24,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Benvingut a"
                                                    
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "slash-icon",
                                                "dataKey": null,
                                                "fileName": "slash-icon",
                                                "height": 30,
                                                "width": 15
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Bienvenido a",
                                                "textSize": 24,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Bienvenido a"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "Sala IDI",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
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
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#222c32",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 0,
                                    "spacing": 5,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "RECULLI TIQUET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "RECULLI TIQUET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "RECOJA TICKET",
                                                "textSize": 32,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "RECOJA TICKET"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },                
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 1,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMENT COMPLET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMENT COMPLET"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 1,
                                    "spacing": 5,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMIENTO COMPLETO",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMIENTO COMPLETO"
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 1005,
                        "screen-id": "IDLE_DISCONNECTED",
                        "data": [
                            {
                                "element-type": "row",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Benvingut a",
                                                "textSize": 24,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Benvingut a"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "slash-icon",
                                                "dataKey": null,
                                                "fileName": "slash-icon",
                                                "height": 30,
                                                "width": 15
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Bienvenido a",
                                                "textSize": 24,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Bienvenido a"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "Sala IDI",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "translations": {
                                        "lang1": "SABA PAU CLARIS"
                                    }
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 6,
                        "screen-id": "DLG_OUT_SERVICE",
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "fileName": "ico-disabled",
                                                "height": 480,
                                                "width": 480
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 7,
                        "screen-id": "DLG_PARKING_FULL",
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "SABA PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
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
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMENT COMPLET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMENT COMPLET"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMIENTO COMPLETO",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMIENTO COMPLETO"
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 8,
                        "screen-id": "USER",
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "SABA PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
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
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 1,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMENT COMPLET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMENT COMPLET"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 1,
                                    "spacing": 5,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "APARCAMIENTO COMPLETO",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 8,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "APARCAMIENTO COMPLETO"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF5800",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "spacing": 5,
                                    "ditTypeCode": 18,
                                    "dataKey": "Status",
                                    "validValue": 0,
                                    "hasShadow": false,
                                    "padding": 12,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "TIQUET → PREMI BOTÓ",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "TIQUET → PREMI BOTÓ"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "TIQUE → PULSE BOTÓN",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "TIQUE → PULSE BOTÓN"
                                                }
                                            }
                                        }
                                    ]
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
                                    "ditTypeCode": 10,
                                    "dataKey": "MainLicensePlate",
                                    "textSize": 72,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": null
                                }
                            }
                        ]
                    },        
                    {
                        "dispatch-code": 9,
                        "screen-id": "READING_PLATE",
                        "data": [
                            {
                                "element-type": "row",
                                "data":{

                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 4,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Benvingut a",
                                                "textSize": 24,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Benvingut a",
                                                    "lang2": "Welcome to"
                                                    
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "slash-icon",
                                                "dataKey": null,
                                                "fileName": "slash-icon",
                                                "height": 30,
                                                "width": 15
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Bienvenido a",
                                                "textSize": 24,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "Bienvenido a",
                                                    "lang2": "Bienvenue à"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "Sala IDI",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "translations": {
                                        "lang1": "SABA PAU CLARIS",
                                        "lang2": "SABA PAU CLARIS"
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
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#222c32",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "RECULLI TIQUET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "RECULLI TIQUET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "RECOJA TICKET",
                                                "textSize": 32,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "RECOJA TICKET"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 50,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "fileName": "ico-arrow-down",
                                                "height": 30,
                                                "width": 30
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "Estem llegint la teva matrícula...",
                                                "textSize": 20,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Estem llegint la seva matrícula..."
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "Estamos leyendo tu matrícula...",
                                                "textSize": 20,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Estamos leyendo su matrícula..."
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 12,
                        "screen-id": "PLEASE_PROCEED",
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#4c9f14",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 4,
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
                                                "translations": {
                                                    "lang1": "POT PASSAR"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "can-pass",
                                                "defaultText": "PUEDES PASAR",
                                                "textSize": 28,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "PUEDE PASAR"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 50,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-value",
                                                "defaultText": "---- ---",
                                                "dataKey": "MainLicensePlate",
                                                "textSize": 72,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": null
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "row",
                                "data": {
                                    "backgroundColor": "#222c32",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "-------",
                                                "ditTypeCode": 7,
                                                "dataKey": "CardReader",
                                                "validValue": 4,
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Via-T"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "-------",
                                                "ditTypeCode": 9,
                                                "dataKey": "CardClass",
                                                "validValue": 0,
                                                "textSize": 36,
                                                "textColor": "#00a599",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "Tiquet\n\rTique"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "-------",
                                                "ditTypeCode": 9,
                                                "dataKey": "CardClass",
                                                "validValue": 1,
                                                "textSize": 36,
                                                "textColor": "#00a599",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "Abonat\n\rAbonado"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 20
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#222c32",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "Gràcies per confiar en nosaltres",
                                                "textSize": 20,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Gràcies per confiar en nosaltres"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "Gracias por confiar en nosotros",
                                                "textSize": 20,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Gracias por confiar en nosotros"
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 18,
                        "screen-id": "DLG_CARD_ERROR",
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF5800",
                                    "density": 100,
                                    "spacing": 4,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "padding": 12,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "HO SENTIM",
                                                "textSize": 40,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "HO SENTIM"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "LO SENTIMOS",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "LO SENTIMOS"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 50,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 12,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "has-an-error",
                                                "defaultText": "VALIDACIÓ NO REALITZADA",
                                                "textSize": 36,
                                                "textColor": "#FF5800",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "VALIDACIÓ NO REALITZADA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "has-an-error",
                                                "defaultText": "VALIDACIÓN NO REALIZADA",
                                                "textSize": 36,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "VALIDACIÓN NO REALIZADA"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 15
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
                                    "padding": 8,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 4
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "fileName": "ico-arrow-down",
                                                "height": 30,
                                                "width": 30
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "contact-customer-service",
                                                "defaultText": "Prem el botó de l'interfòn\n\rper solucionar-ho",
                                                "textSize": 24,
                                                "textColor": "#FF5800",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Prem el botó de l'interfòn\n\rper solucionar-ho"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "contact-customer-service",
                                                "defaultText": "Pulse botón de interfonía\n\rpara solucionarlo",
                                                "textSize": 24,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Pulse botón de interfonía\n\rpara solucionarlo"
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 96,
                        "screen-id": "DLG_BLOCKED",
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "fileName": "ico-disabled",
                                                "height": 480,
                                                "width": 480
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
    }
}