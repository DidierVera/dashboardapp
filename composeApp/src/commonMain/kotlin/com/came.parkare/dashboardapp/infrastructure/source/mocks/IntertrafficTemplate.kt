package com.came.parkare.dashboardapp.infrastructure.source.mocks

object IntertrafficTemplate {
    fun getEntry(): String {
        return """
            {
                "templateName": "INTERTRAFFIC ENTRY",
                "screens": [
                    {
                        "dispatch-code": 5,
                        "screen-id": "IDLE",
                        "margin-top": 250,
                        "margin-bottom": 5,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Welcome to",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Welcome to"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#404040",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "CAME Parkare Car Park"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "TICKETLESS \r\nCAR PARK",
                                                "textSize": 44,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "TICKETLESS \r\nCAR PARK"
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
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 10,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "Entry with license \r\nplate reading",
                                                "textSize": 32,
                                                "textColor": "#404040",
                                                "padding": 32,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Entry with license \r\nplate reading"
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
                        "margin-top": 250,
                        "margin-bottom": 5,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Welcome to",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Welcome to"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#404040",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "CAME Parkare Car Park"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "TICKETLESS \r\nCAR PARK",
                                                "textSize": 44,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "TICKETLESS \r\nCAR PARK"
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
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 10,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "READING \r\n your license plate ...",
                                                "textSize": 32,
                                                "textColor": "#009fe3",
                                                "padding": 32,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "READING \r\n your license plate ..."
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
                        "margin-top": 250,
                        "margin-bottom": 5,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#4c9f14",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
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
                                                "defaultText": "YOU MAY PASS",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "YOU MAY PASS"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "spacing": 4,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "YOUR LICENSE PLATE IS",
                                                "textSize": 20,
                                                "textColor": "#404040",
                                                "padding": 0,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "YOUR LICENSE PLATE IS"
                                                }
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
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "spacing": 8,
                                    "padding": 24,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "via-t-value",
                                                "defaultText": "-------",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardReader",
                                                "ditTypeCode": 7,
                                                "validValue": 4,
                                                "translations": {
                                                    "lang1": "Via-T"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "TICKET",
                                                "textSize": 36,
                                                "textColor": "#009fe3",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 0,
                                                "translations": {
                                                    "lang1": "TICKET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "SUBSCRIBER",
                                                "textSize": 36,
                                                "textColor": "#009fe3",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 1,
                                                "translations": {
                                                    "lang1": "SUBSCRIBER"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "Thank you for trusting us",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Thank you for trusting us"
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
                        "margin-top": 250,
                        "margin-bottom": 5,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Welcome to",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Welcome to"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#404040",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "CAME Parkare Car Park"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "spacing": 0,
                                    "padding": 24,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 15
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "TAKE A TICKET\r\nPRESS BUTTON",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "TAKE A TICKET\r\nPRESS BUTTON"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 15
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
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "spacing": 4,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "YOUR LICENSE PLATE IS",
                                                "textSize": 20,
                                                "textColor": "#404040",
                                                "padding": 0,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "YOUR LICENSE PLATE IS"
                                                }
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

    fun getExit(): String {
        return """
            {
                "templateName": "INTERTRAFFIC EXIT",
                "screens": [
                    {
                        "dispatch-code": 12,
                        "screen-id": "PLEASE_PROCEED",
                        "margin-top": 250,
                        "margin-bottom": 20,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#4c9f14",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
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
                                                "defaultText": "YOU MAY PASS",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "YOU MAY PASS"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "spacing": 4,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "YOUR LICENSE PLATE IS",
                                                "textSize": 20,
                                                "textColor": "#404040",
                                                "padding": 0,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "YOUR LICENSE PLATE IS"
                                                }
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
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 24,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "via-t-value",
                                                "defaultText": "-------",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardReader",
                                                "ditTypeCode": 7,
                                                "validValue": 4,
                                                "translations": {
                                                    "lang1": "Via-T"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "TICKET",
                                                "textSize": 36,
                                                "textColor": "#009fe3",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 0,
                                                "translations": {
                                                    "lang1": "TICKET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "SUBSCRIBER",
                                                "textSize": 36,
                                                "textColor": "#009fe3",
                                                "padding": 0,
                                                "fontWeight": "Bold",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 1,
                                                "translations": {
                                                    "lang1": "SUBSCRIBER"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "Thank you for trusting us",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Thank you for trusting us"
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
                        "margin-top": 250,
                        "margin-bottom": 20,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Thank you for visiting us",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Thank you for visiting us"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 0
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#404040",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "CAME Parkare Car Park"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "EXIT \r\nTICKETLESS",
                                                "textSize": 44,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "EXIT\r\n TICKETLESS"
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
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 10,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "Exit with license \r\nplate reading",
                                                "textSize": 32,
                                                "textColor": "#404040",
                                                "padding": 32,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Exit with license \r\nplate reading"
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
                        "margin-top": 250,
                        "margin-bottom": 20,
                        "margin-left": 30,
                        "margin-right": 30,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Thank you for visiting us",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Regular",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Thank you for visiting us"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 0
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#404040",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "CAME Parkare Car Park"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 70
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 4,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "EXIT \r\nTICKETLESS",
                                                "textSize": 44,
                                                "textColor": "#FFFFFF",
                                                "padding": 24,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "EXIT\r\n TICKETLESS"
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
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": true,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 10,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "READING \r\n your license plate ...",
                                                "textSize": 32,
                                                "textColor": "#009fe3",
                                                "padding": 32,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "READING \r\n your license plate ..."
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