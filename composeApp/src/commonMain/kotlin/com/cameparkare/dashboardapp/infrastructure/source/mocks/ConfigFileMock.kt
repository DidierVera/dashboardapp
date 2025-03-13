package com.cameparkare.dashboardapp.infrastructure.source.mocks

object ConfigFileMock {
    fun getConfigFile(): String {
        return """
            {
    "signalR-url": null,
    "socket-ip": null,
    "socket-port": null,
    "time-delay": 5,
    "video-frame": false,
    "text-size-scale": 10,
    "margin-top": 20,
    "margin-bottom": 100,
    "margin-left": 20,
    "margin-right": 20,
    "screens": [
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
                                        "lang1": "POT PASSAR",
                                        "lang2": "YOU CAN PASS"
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
                                        "lang1": "PUEDE PASAR",
                                        "lang2": "VOUS POUVEZ PASSER"
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
                        "textSize": 84,
                        "textColor": "#000000",
                        "padding": 4,
                        "fontWeight": "Bold",
                        "translations": null
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
                        "spacing": 12,
                        "padding": 8,
                        "content": [
                            {
                                "element-type": "image",
                                "data": {
                                    "dashboardItemId": "viat-logo",
                                    "ditTypeCode": 7,
                                    "dataKey": "CardReader",
                                    "fileName": "Via-T_white",
                                    "validValue": 4,
                                    "height": 30,
                                    "width": 60
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
                                        "lang1": "Tiquet",
                                        "lang2": "Paper ticket"
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
                                        "lang1": "Abonat",
                                        "lang2": "Abonnèe"
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
                                        "lang1": "Gràcies per confiar en nosaltres",
                                        "lang2": "Thanks for trusting us"
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
                                        "lang1": "Gracias por confiar en nosotros",
                                        "lang2": "Merci de nous faire confiance"
                                    }
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
                                        "lang1": "RECULLI TIQUET",
                                        "lang2": "TAKE THE TICKET"
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
                                        "lang1": "RECOJA TICKET",
                                        "lang2": "PRENEZ LE TICKET"
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
                        "backgroundColor": "#FF5800",
                        "density": 100,
                        "roundBorder": 0,
                        "hasShadow": false,
                        "spacing": 5,
                        "ditTypeCode": 18,
                        "dataKey": "Status",
                        "validValue": 1,
                        "padding": 4,
                        "content": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "PARKING COMPLET",
                                    "textSize": 32,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "PARKING COMPLET",
                                        "lang2": "FULL PARKING"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "NOMÉS ABONATS",
                                    "textSize": 32,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "NOMÉS ABONATS",
                                        "lang2": "SUBSCRIBERS ONLY"
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
                        "backgroundColor": "#FF5800",
                        "density": 100,
                        "roundBorder": 0,
                        "hasShadow": false,
                        "ditTypeCode": 18,
                        "dataKey": "Status",
                        "validValue": 1,
                        "spacing": 5,
                        "padding": 4,
                        "content": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "PARKING COMPLETO",
                                    "textSize": 32,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "PARKING COMPLETO",
                                        "lang2": "STATIONNEMENT COMPLET"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "SOLO ABONADOS",
                                    "textSize": 32,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "SOLO ABONADOS",
                                        "lang2": "RÉSERVÉ AUX ABONNÉS"
                                    }
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
                        "backgroundColor": "#FF5800",
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
                                    "defaultText": "PARKING COMPLET",
                                    "textSize": 32,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "PARKING COMPLET",
                                        "lang2": "FULL PARKING"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "NOMÉS ABONATS",
                                    "textSize": 32,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "NOMÉS ABONATS",
                                        "lang2": "SUBSCRIBERS ONLY"
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
                        "backgroundColor": "#FF5800",
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
                                    "defaultText": "PARKING COMPLETO",
                                    "textSize": 32,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "PARKING COMPLETO",
                                        "lang2": "STATIONNEMENT COMPLET"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "ticket-less-label",
                                    "defaultText": "SOLO ABONADOS",
                                    "textSize": 32,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "SOLO ABONADOS",
                                        "lang2": "RÉSERVÉ AUX ABONNÉS"
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
                        "ditTypeCode": 18,
                        "dataKey": "Status",
                        "validValue": 0,
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
                                        "lang1": "RECULLI TIQUET",
                                        "lang2": "TAKE THE TICKET"
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
                                        "lang1": "RECOJA TICKET",
                                        "lang2": "PRENEZ LE TICKET"
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
                                    "dashboardItemId": "license-plate-reader-label",
                                    "defaultText": "Estem llegint la teva matrícula...",
                                    "textSize": 20,
                                    "textColor": "#222c32",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "translations": {
                                        "lang1": "Estem llegint la seva matrícula...",
                                        "lang2": "We're reading your license plate..."
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
                                        "lang1": "Estamos leyendo su matrícula...",
                                        "lang2": "Nous lisons votre plaque d'immatriculation..."
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
                    "element-type": "column",
                    "data": {
                        "backgroundColor": "#FF5800",
                        "density": 100,
                        "roundBorder": 0,
                        "spacing": 5,
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
                                        "lang1": "TIQUET → PREMI BOTÓ",
                                        "lang2": "TICKET → PRESS BUTTON"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "fail-sorry",
                                    "defaultText": "TICKET → PULSE BOTÓN",
                                    "textSize": 32,
                                    "textColor": "#FFFFFF",
                                    "padding": 12,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "TICKET → PULSE BOTÓN",
                                        "lang2": "TICKET → APPUYEZ SUR LE BOUTON"
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
                                    "ditTypeCode": 10,
                                    "dataKey": "MainLicensePlate",
                                    "textSize": 84,
                                    "textColor": "#000000",
                                    "padding": 4,
                                    "fontWeight": "Bold",
                                    "translations": null
                                }
                            }
                        ]
                    }
                }
            ]
        },
        {
            "dispatch-code": 6,
            "screen-id": "DLG_OUT_SERVICE",
            "data": [
                {
                    "element-type": "image",
                    "data": {
                        "dashboardItemId": "arrow-down",
                        "dataKey": null,
                        "fileName": "ico-disabled",
                        "height": 500,
                        "width": 500
                    }
                }		
            ]
        },
        {
            "dispatch-code": 97,
            "screen-id": "DLG_OUT_SERVICE",
            "data": [
                {
                    "element-type": "image",
                    "data": {
                        "dashboardItemId": "arrow-down",
                        "dataKey": null,
                        "fileName": "ico-disabled",
                        "height": 500,
                        "width": 500
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
                                    "defaultText": "LO SENTIMOS",
                                    "textSize": 40,
                                    "textColor": "#FFFFFF",
                                    "padding": 12,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "LO SENTIMOS",
                                        "lang2": "WE ARE SORRY"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "fail-sorry",
                                    "defaultText": "HO\n\rSENTIM",
                                    "textSize": 40,
                                    "textColor": "#222c32",
                                    "padding": 12,
                                    "fontWeight": "Bold",
                                    "translations": {
                                        "lang1": "HO\n\rSENTIM",
                                        "lang2": "NOUS SOMMES\n\rDÉSOLÉ"
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
                                    "defaultText": "HA HABIDO UN ERROR",
                                    "textSize": 36,
                                    "textColor": "#222c32",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "translations": {
                                        "lang1": "HA HABIDO UN ERROR",
                                        "lang2": "HAS BEEN AN ERROR"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "has-an-error",
                                    "defaultText": "HA HAGUT UN ERROR",
                                    "textSize": 36,
                                    "textColor": "#FF5800",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "translations": {
                                        "lang1": "HA HAGUT UN ERROR",
                                        "lang2": "IL Y A EU UNE ERREUR"
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
                                    "defaultText": "Pulse botón de interfonía\n\rpara solucionarlo",
                                    "textSize": 24,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "translations": {
                                        "lang1": "Pulse botón de interfonía\n\rpara solucionarlo",
                                        "lang2": "Press the intercom button\n\rto fix it"
                                    }
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
                                        "lang1": "Prem el botó de l'interfòn\n\rper solucionar-ho",
                                        "lang2": "Appuyez sur le bouton de\n\rl'interphone pour résoudre le problème"
                                    }
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
    fun getFtpConfigValues(): String {
        return """
            {
                "device-root": 
                {
                    "username": "Parkare",
                    "password": "Parking2010",
                    "port": 2121
                },
                "app-root":
                {
                    "username": "admin",
                    "password": "Parking2010",
                    "port": 2122
                }
            }
        """.trimIndent()
    }
}