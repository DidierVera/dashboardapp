package com.came.parkare.dashboardapp.infrastructure.source.mocks

object IntertrafficTemplate {
    fun getEntry(): String {
        return """
            {
                "templateName": "INTERTRAFFIC ENTRY",
                "screens": [
                    {
                        "dispatch-code": 0,
                        "screen-id": "DLG_BOOT",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 4,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "ico-disabled",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "STARTING",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 8,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "STARTING"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Welcome to",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Welcome to"
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
                                    "textSize": 40,
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
                                    "value": 160
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
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
                                                "textSize": 52,
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
                                    "value": 8
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
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
                                                "textSize": 40,
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
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 1,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "FULL PARKING",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 30,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "FULL PARKING"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "welcome-label",
                                                "defaultText": "Welcome to",
                                                "textSize": 32,
                                                "textColor": "#009fe3",
                                                "padding": 12,
                                                "fontWeight": "Medium",
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
                                                "textSize": 40,
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
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": []
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 6,
                        "screen-id": "DLG_OUT_SERVICE",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 4,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "ico-disabled",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "OUT OF SERVICE",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 8,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "OUT OF SERVICE"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 40,
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
                                    "value": 52
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "FULL PARKING",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 30,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "FULL PARKING"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 40,
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
                                    "value": 30
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 1,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "ticket-less-label",
                                                "defaultText": "FULL PARKING",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 30,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "FULL PARKING"
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
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 5,
                                    "padding": 8,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 1,
                                    "content": []
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
                                    "padding": 24,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "TICKET → PRESS BUTTON",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "TICKET → PRESS BUTTON"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 30
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 4,
                                    "padding": 20,
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
                                                "padding": 8,
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
                                                "defaultText": "3003 ABC",
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
                    },
                    {
                        "dispatch-code": 9,
                        "screen-id": "READING_PLATE",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Welcome to",
                                    "textSize": 32,
                                    "textColor": "#009fe3",
                                    "padding": 12,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Welcome to"
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
                                    "textSize": 40,
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
                                    "value": 160
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
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
                                                "textSize": 52,
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
                                    "value": 8
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
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
                                                "textSize": 40,
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
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
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "can-pass",
                                                "defaultText": "YOU MAY PASS",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 32,
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
                                    "value": 100
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 4,
                                    "padding": 20,
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
                                                "padding": 8,
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
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "TICKET",
                                                "textSize": 48,
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
                                                "textSize": 48,
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
                        "dispatch-code": 18,
                        "screen-id": "DLG_CARD_ERROR",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "WE'RE SORRY",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "WE'RE SORRY"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 60
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
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
                                                "dashboardItemId": "has-an-error",
                                                "defaultText": "VALIDATION\r\nNOT PERFORMED",
                                                "textSize": 36,
                                                "textColor": "#404040",
                                                "padding": 24,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "VALIDATION\r\nNOT PERFORMED"
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
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 12,
                                    "padding": 24,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "ico-arrow-down",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 40,
                                                "width": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "contact-customer-service",
                                                "defaultText": "Please go to customer service\r\nto resolve this issue",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Please go to customer service\r\nto resolve this issue"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 4,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "ico-disabled",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "thanks-label",
                                                "defaultText": "BLOCKED",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 8,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "BLOCKED"
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

    fun getExit(): String {
        return """
            {
                "templateName": "INTERTRAFFIC EXIT",
                "screens": [
                    {
                        "dispatch-code": 12,
                        "screen-id": "PLEASE_PROCEED",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
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
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "can-pass",
                                                "defaultText": "YOU MAY PASS",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 32,
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
                                    "value": 100
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 4,
                                    "padding": 20,
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
                                                "padding": 8,
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
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "TICKET",
                                                "textSize": 48,
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
                                                "textSize": 48,
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
                        "dispatch-code": 36,
                        "screen-id": "DLG_PAYMENT_REQUIRED",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#222c32",
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
                                    "value": 50
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 30,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "AMOUNT\r\nOUTSTANDING TO PAY",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "AMOUNT\r\nOUTSTANDING TO PAY"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 10
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FF5800",
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
                                    "content": []
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 89,
                        "screen-id": "DLG_InicioCobroActual",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "CAME Parkare Car Park",
                                    "textSize": 36,
                                    "textColor": "#222c32",
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
                                    "value": 50
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 30,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "AMOUNT\r\nOUTSTANDING TO PAY",
                                                "textSize": 40,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "AMOUNT\r\nOUTSTANDING TO PAY"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 10
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FF5800",
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
                                    "content": []
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 5,
                        "screen-id": "IDLE",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
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
                                    "textSize": 40,
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
                                    "value": 160
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
                                                "textSize": 52,
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
                                    "value": 8
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
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
                                                "textSize": 40,
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
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
                                    "textSize": 40,
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
                                    "value": 160
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
                                                "textSize": 52,
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
                                    "value": 8
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
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
                                                "textSize": 40,
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
                        "dispatch-code": 8,
                        "screen-id": "USER",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "WE'RE SORRY",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "WE'RE SORRY"
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
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "FOLLOW THE TERMINAL'S\r\nINSTRUCTIONS",
                                                "textSize": 28,
                                                "textColor": "#222c32",
                                                "padding": 12,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "FOLLOW THE TERMINAL'S\r\nINSTRUCTIONS"
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
                                    "density": 70,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 4,
                                    "padding": 20,
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
                                                "padding": 8,
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
                                                "defaultText": "3003 ABC",
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
                    },
                    {
                        "dispatch-code": 6,
                        "screen-id": "DLG_OUT_SERVICE",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
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
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "parking-name",
                                                "defaultText": "OUT OF SERVICE",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "OUT OF SERVICE"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
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
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "parking-name",
                                                "defaultText": "BLOCKED",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "BLOCKED"
                                                }
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 0,
                        "screen-id": "DLG_BOOT",
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 30,
                                    "padding": 12,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
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
                                                "height": 350,
                                                "width": 350
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "parking-name",
                                                "defaultText": "STARTING",
                                                "textSize": 30,
                                                "textColor": "#404040",
                                                "padding": 4,
                                                "fontWeight": "Regular",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "STARTING"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#da0025",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "width": null,
                                    "height": null,
                                    "margin": null,
                                    "padding": 24,
                                    "content": [
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "fail-sorry",
                                                "defaultText": "WE'RE SORRY",
                                                "textSize": 52,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Bold",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "WE'RE SORRY"
                                                }
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 60
                                }
                            },
                            {
                                "element-type": "box",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 70,
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
                                                "dashboardItemId": "has-an-error",
                                                "defaultText": "VALIDATION\r\nNOT PERFORMED",
                                                "textSize": 36,
                                                "textColor": "#404040",
                                                "padding": 24,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "VALIDATION\r\nNOT PERFORMED"
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
                                    "backgroundColor": "#404040",
                                    "density": 100,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 12,
                                    "padding": 24,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "arrow-down",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "ico-arrow-down",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 40,
                                                "width": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "contact-customer-service",
                                                "defaultText": "Please go to customer service\r\nto resolve this issue",
                                                "textSize": 32,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "Please go to customer service\r\nto resolve this issue"
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
                        "margin-top": 20,
                        "margin-bottom": 20,
                        "margin-left": 48,
                        "margin-right": 48,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
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
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "parking-name",
                                                "defaultText": "CAME Parkare Car Park",
                                                "textSize": 40,
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
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#404040",
                                    "density": 0,
                                    "roundBorder": 0,
                                    "hasShadow": false,
                                    "spacing": 8,
                                    "padding": 28,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": []
                                }
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
    }
}