package com.came.parkare.dashboardapp.infrastructure.source.mocks

object EntryWithTicketTemplate {
    fun get(): String{
        return """
            {
                "templateName":"ENTRY WITH TICKET",
                "screens":[
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
                                            "element-type": "row",
                                            "data": {
                                                "backgroundColor": "#222c32",
                                                "density": 0,
                                                "roundBorder": 0,
                                                "hasShadow": false,
                                                "spacing": 12,
                                                "padding": 4,
                                                "content": [
                                                    {
                                                        "element-type": "text",
                                                        "data": {
                                                            "dashboardItemId": "entry-type-label",
                                                            "defaultText": "TIPUS D'ENTRADA:",
                                                            "textSize": 12,
                                                            "textColor": "#FFFFFF",
                                                            "padding": 0,
                                                            "fontWeight": "Regular",
                                                            "translations": {
                                                                "lang1": "TIPUS D'ENTRADA:",
                                                                "lang2": "ACCESS TYPE:"
                                                            }
                                                        }
                                                    },
                                                    {
                                                        "element-type": "image",
                                                        "data": {
                                                            "dashboardItemId": "viat-logo",
                                                            "dataKey": "CardReader",
                                                            "fileName": "Via-T_white",
                                                            "height": 30,
                                                            "width": 60
                                                        }
                                                    },
                                                    {
                                                        "element-type": "text",
                                                        "data": {
                                                            "dashboardItemId": "entry-type-value",
                                                            "defaultText": "-------",
                                                            "dataKey": "CardClass",
                                                            "textSize": 32,
                                                            "textColor": "#00a599",
                                                            "padding": 0,
                                                            "fontWeight": "Bold",
                                                            "translations": null
                                                        }
                                                    }
                                                ]
                                            }
                                        },
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
                                            "element-type": "row",
                                            "data": {
                                                "backgroundColor": "#222c32",
                                                "density": 0,
                                                "roundBorder": 0,
                                                "hasShadow": false,
                                                "spacing": 12,
                                                "padding": 4,
                                                "content": [
                                                    {
                                                        "element-type": "text",
                                                        "data": {
                                                            "dashboardItemId": "entry-type-label",
                                                            "defaultText": "TIPO DE ENTRADA:",
                                                            "textSize": 12,
                                                            "textColor": "#FFFFFF",
                                                            "padding": 0,
                                                            "fontWeight": "Regular",
                                                            "translations": {
                                                                "lang1": "TIPO DE ENTRADA:",
                                                                "lang2": "TYPE D'ACCÈS:"
                                                            }
                                                        }
                                                    },
                                                    {
                                                        "element-type": "image",
                                                        "data": {
                                                            "dashboardItemId": "viat-logo",
                                                            "dataKey": "CardReader",
                                                            "fileName": "Via-T_white",
                                                            "height": 30,
                                                            "width": 60
                                                        }
                                                    },
                                                    {
                                                        "element-type": "text",
                                                        "data": {
                                                            "dashboardItemId": "entry-type-value",
                                                            "defaultText": "-------",
                                                            "dataKey": "CardClass",
                                                            "textSize": 32,
                                                            "textColor": "#00a599",
                                                            "padding": 0,
                                                            "fontWeight": "Bold",
                                                            "translations": null
                                                        }
                                                    }
                                                ]
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
                                                "defaultText": "Pase por atención al cliente\n\rpara solucionarlo",
                                                "textSize": 20,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Pase por atención al cliente\n\rpara solucionarlo",
                                                    "lang2": "Approach the customer service office\n\rto resolve it."
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "contact-customer-service",
                                                "defaultText": "Passi per atenció al client per solucionar-ho",
                                                "textSize": 20,
                                                "textColor": "#FF5800",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Passi per atenció al client\n\rper solucionar-ho",
                                                    "lang2": "Veuillez vous adresser au service client\n\rpour résoudre le problème."
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
}