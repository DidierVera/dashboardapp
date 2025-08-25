package com.came.parkare.dashboardapp.infrastructure.source.mocks

object BlankTemplate {
    fun getBlankTemplate(): String{
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
                                                "dashboardItemId": "ico-disabled",
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