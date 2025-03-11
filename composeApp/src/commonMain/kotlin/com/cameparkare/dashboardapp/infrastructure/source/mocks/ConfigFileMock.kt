package com.cameparkare.dashboardapp.infrastructure.source.mocks


object ConfigFileMock {
    fun getConfigFile(): String {
        return """
            {
                "signalR-url": "http://192.168.209.164:9011/signalR",
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
                }
                ]
            }
        """.trimIndent()
    }
    fun getCardClassDefault(): String {
        return """
            {
                "subscriber": {
                    "lang1": "Abonat / Abonado",
                    "lang2": "Subscriber / Abonnée",
                    "default": "Subscriber"
                },
                "ticket": {
                    "lang1": "Ticket",
                    "lang2": "Ticket",
                    "default": "Ticket"
                }
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