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
                        "dashboardItemId": "default_image_id",
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
}