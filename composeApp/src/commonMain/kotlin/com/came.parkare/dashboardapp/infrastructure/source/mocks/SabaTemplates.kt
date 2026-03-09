package com.came.parkare.dashboardapp.infrastructure.source.mocks

object SabaTemplates {
    fun getEntry(): String {
        return """
            {
                "templateName": "INTERTRAFFIC ENTRY",
                "screens": [
                    {
                        "dispatch-code": 5,
                        "screen-id": "IDLE",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Benvingut/uda a",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Benvingut/uda a",
                                        "lang2": "Bienvenido/da a"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 3,
                                    "padding": 10,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "ENTRADA SENSE TIQUET",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "ENTRADA SENSE TIQUET",
                                                    "lang2": "ENTRADA SIN TÍQUE"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "AMB",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "AMB",
                                                    "lang2": "CON"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "LECTURA DE MATRÍCULA",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "LECTURA DE MATRÍCULA",
                                                    "lang2": "LECTURA DE MATRÍCULA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 3,
                                    "padding": 10,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 1,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "APARCAMENT\r\nCOMPLET",
                                                "textSize": 34,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "APARCAMENT\r\nCOMPLET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 70
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Benvingut/uda a",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Benvingut/uda a",
                                        "lang2": "Bienvenido/da a"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 6
                                            }
                                        },
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
                                                "height": 70,
                                                "width": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "LLEGINT MATRÍCULA",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "LLEGINT MATRÍCULA",
                                                    "lang2": "LEYENDO MATRÍCULA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": " ",
                                                "textSize": 28,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": " "
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 48
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 65
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "ENDAVANT",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "ENDAVANT"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                                "element-type": "spacer",
                                "data": {
                                    "value": 60
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "SABA TICKETLESS",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 0,
                                                "translations": {
                                                    "lang1": "SABA TICKETLESS"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "ABONAMENT",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 1,
                                                "translations": {
                                                    "lang1": "ABONAMENT"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Benvingut/uda a",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Benvingut/uda a",
                                        "lang2": "Bienvenido/da a"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF751F",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 85
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "TIQUET-PREMEU BOTÓ",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "TIQUET-PREMEU BOTÓ"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 85
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
                        "dispatch-code": 18,
                        "screen-id": "DLG_CARD_ERROR",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF751F",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "VALIDACIÓ\r\nNO REALITZADA",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "VALIDACIÓ\r\nNO REALITZADA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 90
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 6
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "icon-info",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "icon-info",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 70,
                                                "width": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "PREMEU L'INTÈRFON\r\nPER REBRE AJUDA",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "PREMEU L'INTÈRFON\r\nPER REBRE AJUDA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Benvingut/uda a",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Benvingut/uda a",
                                        "lang2": "Bienvenido/da a"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF0000",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 3,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "APARCAMENT\r\nCOMPLET",
                                                "textSize": 34,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "APARCAMENT\r\nCOMPLET"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 70
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "Benvingut/uda a",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "Benvingut/uda a"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 0,
                        "screen-id": "DLG_BOOT",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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
                        "dispatch-code": 6,
                        "screen-id": "DLG_OUT_SERVICE",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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
                        "dispatch-code": 96,
                        "screen-id": "DLG_BLOCKED",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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

    fun getExit(): String {
        return """
            {
                "templateName": "SABA PAU CLARIS EXIT",
                "screens": [
                    {
                        "dispatch-code": 5,
                        "screen-id": "IDLE",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 3,
                                    "padding": 10,
                                    "dataKey": "Status",
                                    "ditTypeCode": 18,
                                    "validValue": 0,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "GRÀCIES PER",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "GRÀCIES PER",
                                                    "lang2": "GRACIAS POR"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "LA SEVA VISITA!",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "LA SEVA VISITA!",
                                                    "lang2": "SU VISITA!"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 10
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "icon-car",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "icon-car",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 45,
                                                "width": 60
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 24,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 6
                                            }
                                        },
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
                                                "height": 70,
                                                "width": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "LLEGINT MATRÍCULA",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "LLEGINT MATRÍCULA",
                                                    "lang2": "LEYENDO MATRÍCULA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 80
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 3,
                                    "padding": 10,
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
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "GRÀCIES PER",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "GRÀCIES PER"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "LA SEVA VISITA!",
                                                "textSize": 34,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "LA SEVA VISITA!"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 5
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "icon-car",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "icon-car",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 40,
                                                "width": 55
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
                                    "value": 45
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
                                "element-type": "spacer",
                                "data": {
                                    "value": 45
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "SABA TICKETLESS",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 0,
                                                "translations": {
                                                    "lang1": "SABA TICKETLESS"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "entry-type-value",
                                                "defaultText": "ABONAMENT",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 0,
                                                "fontWeight": "Medium",
                                                "dataKey": "CardClass",
                                                "ditTypeCode": 9,
                                                "validValue": 1,
                                                "translations": {
                                                    "lang1": "ABONAMENT"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
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
                                    "backgroundColor": "#FF5800",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 60
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "IMPORT PENDENT\r\nDE PAGAMENT",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "IMPORT PENDENT\r\nDE PAGAMENT"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 60
                                            }
                                        }
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 89,
                        "screen-id": "DLG_InicioCobroActual",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
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
                                    "backgroundColor": "#FF5800",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 60
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "PROCESSING\r\nPAYMENT",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "PROCESSING\r\nPAYMENT"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 60
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 120
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF751F",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 85
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "cannot-read",
                                                "defaultText": "SEGUIU LES INSTRUCCIONS\r\nDEL TERMINAL",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 12,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "SEGUIU LES INSTRUCCIONS\r\nDEL TERMINAL"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 85
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FF751F",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "VALIDACIÓ\r\nNO REALITZADA",
                                                "textSize": 36,
                                                "textColor": "#FFFFFF",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "VALIDACIÓ\r\nNO REALITZADA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
                                            }
                                        }
                                    ]
                                }
                            },
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 90
                                }
                            },
                            {
                                "element-type": "column",
                                "data": {
                                    "backgroundColor": "#FFFFFF",
                                    "density": 100,
                                    "roundBorder": 25,
                                    "hasShadow": true,
                                    "spacing": 10,
                                    "padding": 10,
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "content": [
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 6
                                            }
                                        },
                                        {
                                            "element-type": "image",
                                            "data": {
                                                "dashboardItemId": "icon-info",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "fileName": "icon-info",
                                                "localFilePath": null,
                                                "intervalTime": null,
                                                "height": 70,
                                                "width": 70
                                            }
                                        },
                                        {
                                            "element-type": "text",
                                            "data": {
                                                "dashboardItemId": "license-plate-reader-label",
                                                "defaultText": "PREMEU L'INTÈRFON\r\nPER REBRE AJUDA",
                                                "textSize": 36,
                                                "textColor": "#000000",
                                                "padding": 4,
                                                "fontWeight": "Medium",
                                                "dataKey": null,
                                                "ditTypeCode": null,
                                                "validValue": null,
                                                "translations": {
                                                    "lang1": "PREMEU L'INTÈRFON\r\nPER REBRE AJUDA"
                                                }
                                            }
                                        },
                                        {
                                            "element-type": "spacer",
                                            "data": {
                                                "value": 40
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
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "welcome-label",
                                    "defaultText": "GRÀCIES PER\r\nLA SEVA VISITA!",
                                    "textSize": 36,
                                    "textColor": "#FFFFFF",
                                    "padding": 4,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "GRÀCIES PER\r\nLA SEVA VISITA!"
                                    }
                                }
                            },
                            {
                                "element-type": "text",
                                "data": {
                                    "dashboardItemId": "parking-name",
                                    "defaultText": "PAU CLARIS",
                                    "textSize": 36,
                                    "textColor": "#ffffff",
                                    "padding": 0,
                                    "fontWeight": "Medium",
                                    "dataKey": null,
                                    "ditTypeCode": null,
                                    "validValue": null,
                                    "translations": {
                                        "lang1": "PAU CLARIS"
                                    }
                                }
                            }
                        ]
                    },
                    {
                        "dispatch-code": 0,
                        "screen-id": "DLG_BOOT",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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
                        "dispatch-code": 6,
                        "screen-id": "DLG_OUT_SERVICE",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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
                        "dispatch-code": 96,
                        "screen-id": "DLG_BLOCKED",
                        "margin-top": 200,
                        "margin-bottom": 5,
                        "margin-left": 35,
                        "margin-right": 35,
                        "data": [
                            {
                                "element-type": "spacer",
                                "data": {
                                    "value": 100
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
                                    "padding": 4,
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