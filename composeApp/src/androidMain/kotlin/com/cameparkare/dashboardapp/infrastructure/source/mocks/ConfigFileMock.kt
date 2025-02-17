package com.cameparkare.dashboardapp.infrastructure.source.mocks


object ConfigFileMock {
    fun getConfigFile(): String {
        return """
                        {
                "signalR-url": null,
                "socket-ip": null,
                "socket-port": null,
                "time-delay": 4,
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
                                        "lang1": "PAU CLARIS",
                                        "lang2": "PAU CLARIS"
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
                                                "defaultText": "PARKING SENSE TICKET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "PARKING SENSE TICKET",
                                                    "lang2": "TICKETLESS PARKING"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "PARKING SIN TICKET",
                                                "textSize": 32,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "PARKING SIN TICKET",
                                                    "lang2": "STATIONNEMENT SANS TICKET"
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
                                                "defaultText": "Entrada amb lectura de matrícula",
                                                "textSize": 20,
                                                "textColor": "#222c32",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Entrada amb lectura de matrícula",
                                                    "lang2": "Entry with license plate reading"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "Entrada con lectura de matrícula",
                                                "textSize": 20,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Entrada con lectura de matrícula",
                                                    "lang2": "Entrée avec lecteur de plaque d'immatriculation"
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
                                        "lang1": "PAU CLARIS",
                                        "lang2": "PAU CLARIS"
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
                                                "defaultText": "PARKING SENSE TICKET",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "PARKING SENSE TICKET",
                                                    "lang2": "TICKETLESS PARKING"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "PARKING SIN TICKET",
                                                "textSize": 32,
                                                "textColor": "#00a599",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "PARKING SIN TICKET",
                                                    "lang2": "STATIONNEMENT SANS TICKET"
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
                                                "defaultText": "HO SENTIM",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "HO SENTIM",
                                                    "lang2": "WE ARE SORRY"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "LO SENTIMOS",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "LO SENTIMOS",
                                                    "lang2": "NOUS SOMMES DÉSOLÉ"
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
                                    "padding": 12,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "NO PODEM LLEGIR LA TEVA MATRÍCULA",
                                                "textSize": 28,
                                                "textColor": "#222c32",
                                                "padding": 12,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "NO PODEM LLEGIR LA TEVA MATRÍCULA",
                                                    "lang2": "WE CANNOT READ YOUR LICENSE PLATE"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "NO PODEMOS LEER TU MATRÍCULA",
                                                "textSize": 28,
                                                "textColor": "#222c32",
                                                "padding": 12,
                                                "fontWeight": "Regular",
                                                "translations": {
                                                    "lang1": "NO PODEMOS LEER TU MATRÍCULA",
                                                    "lang2": "NOUS NE POUVONS PAS LIRE VOTRE PLAQUE D'IMMATRICULATION"
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
                                                "dashboardItemId": "check-terminal-instructions",
                                                "defaultText": "Prem el botó o dirigeix-te a control per a solucionar-lo",
                                                "textSize": 16,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Prem el botó o dirigeix-te a control per a solucionar-lo",
                                                    "lang2": "Press the button or go to control to fix it"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "check-terminal-instructions",
                                                "defaultText": "Pulsa el botón o dirígete a control para solucionarlo",
                                                "textSize": 16,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Pulsa el botón o dirígete a control para solucionarlo",
                                                    "lang2": "Appuyez sur le bouton ou accédez au contrôle pour résoudre le problème"
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
                                                "defaultText": "HO SENTIM",
                                                "textSize": 40,
                                                "textColor": "#222c32",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "translations": {
                                                    "lang1": "HO SENTIM",
                                                    "lang2": "NOUS SOMMES DÉSOLÉ"
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
                                                "defaultText": "Pase por atención al cliente para solucionarlo",
                                                "textSize": 20,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "translations": {
                                                    "lang1": "Pase por atención al cliente para solucionarlo",
                                                    "lang2": "Approach the customer service office to resolve it."
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
                                                    "lang1": "Passi per atenció al client per solucionar-ho",
                                                    "lang2": "Veuillez vous adresser au service client pour résoudre le problème."
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

    fun getCardClassDefault(): String{
        return """
            {
                "subscriber": {
                    "es": "Abonado",
                    "en": "Subscriber",
                    "default": "Subscriber"
                },
                "ticket": {
                    "es": "Ticket",
                    "en": "Ticket",
                    "default": "Ticket"
                }
            }
        """.trimIndent()
    }
}