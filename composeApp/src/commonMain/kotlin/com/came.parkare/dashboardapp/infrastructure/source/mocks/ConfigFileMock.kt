package com.came.parkare.dashboardapp.infrastructure.source.mocks

object ConfigFileMock {

    fun getConnectionConfiguration(): String {
        return """
            {
                "connection-way": 0,
                "terminal-ip": "192.168.209.216",
                "port": 9011,
                "api": "signalr",
                "time-delay": 5,
                "api-port": 2023,
                "video-frame": false,
                "text-size-scale": 10,
                "auto-brightness-mode": true, 
                "active-low-brightness-time": 60,
                "car-counter-reset": 2,
                "show-car-counter": false,
                "files": [
                    {
                        "fileName": "ico-arrow-down.png",
                        "fileContent": "iVBORw0KGgoAAAANSUhEUgAAAE4AAABOCAIAAAAByLdKAAAACXBIWXMAAAsSAAALEgHS3X78AAAEYUlEQVR4nO2aMW/aQBiGrw4YESxwjCjuQjwwscQTE1JcJibKwF73F8T9BYFfUPcXxNkjJe3EFip5Yqm7MDE46VAqJMDITYSTWB0sIaut6d35TCXXz5jY1r258/d97xs/A2dfwf8B9a8XsDsSqXEkkRpHEqlxJJEaRxKpcSSRGkcSqXEkkRpHEqnBCEwqinWggrEMZKlydX/YKolcGvVGUohcetgqydV91BtxDvAxn/ncLqt1lqV3ev5ZmlLr7Od2+ZjPYNyOv9aTGmO0y51KFvsJSHQqWaNdPqkx2E8ItS2HzN5ls3jVLEb6AgtM6qpZvGwWD5m9MM8hcAJfVbJG+3lPzId/1O/0xLzRfv6KxNkh87IVaOpUzBvtsoT1Fv0Ric8Y7fKpmC8Qqggk68oRl75ulbQGF7JcsTSlNbjrVumIaJ0nX0JfV/fNLi9Xc3i3y9Wc2eVfo/eSv4Is1Zg//PWaAk2dNQ6GrRJSuRKY1LBVOmscwJxYmGX8wjOMf08JTEprHEA2t76xUsf20nG3XMPSlFJjTuEK26fpWtYXpv0ItVYfOFI9OpWsBrcDN/aTrM+H0/UffyvxGa3BwTQSy3FlfXF1e4+8VgBAGKkAAJamemIesq1/uL2X9YV/e1ma0hoHkI3k/djuGavtp2M7oaR6SHxGrbMw1dJy3J6xUsc2AECpMT24RvJl/qCMlkGHAh4CUj2Qlg4AQP3ThIeYVACAwKTUeoHIZAMA+HB7r4wsjPITBEmpHvBlJojtZQwb8iPEcLoWP37vGyu82/vGSvz4nbhOEMWubhC5tFpn4b3lp+laGS0xZgNIIpTqIVdzar2wvVxZjquMLG3yI9KVYAQuOST7ok1+CBfT88ld0AXnkzvhYoqkU+IzGDM2Roy2d90qIdnxpePK+vzlYHZjP/l/fmM/vRzMZH0OPxh4Nv26VRLQyx5mWfLsuIISfwyna+HiW99YWY5rOW7fWAkX35DKj1Jjwth0/KCkQFPv6qxczcn6HL6W9IyVNrkDACA1TJFLaw0upH0N22yOuDRqemjaj/A6NylheJtOpq+e1BizyxNPDzuVrNnlw6SEfoiNEAWaumwWUe14EJ5Nv2wWSQVLgPi0dMxnwqeHXkqIl2tvgfxg6KWHZvcFRnoo8Rmz+4JgSugH+Ynq2N4yD2w4ZPaQ0sNNSgjjE84ndxjODnMwxLPjQezGpoeagXtiXqkxMEsMGuXhLYHluOrY7uEaJhB+3MdOD3eTEvoh42w6laxaZ2Fesxv7SRktAQBI12OnhH6ImTik9BCS8CmhH8J+FdWOBxGFTSfcvoz5gzSYvR0tLdytsBz37WgpDWbE44ioUgikOHvD77E4QaINXODTw4hSQj/RfrexsePbL8Ow6RhEHqN5BLVfIg0Tkh19jWPaj9Jg9kZfbMqV5bhv9IU0mO1GJ9jZrm7w2i8AgGDDhGTXUv8hyeeUcSSRGkcSqXEkkRpHEqlxJJEaRxKpceQ/kvoTU0X70DFfi9UAAAAASUVORK5CYII="
                    },
                    {
                        "fileName": "slash-icon.png",
                        "fileContent": "iVBORw0KGgoAAAANSUhEUgAAACkAAABwCAYAAACD4VnJAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAN1SURBVHgBzZvNcRQxEIVbgwMgBDKADFAGcKGKo1MgAtIxxcVkMJuBycAh+Aa+WMzMevBYO/vUkvpnv8N6reryvGqpJM1X5UBOpK/0bvpxzSi9vyIvAn2gRN+LdYm+DeRFos+suica/UISfSxWJHoIP+nOJWT6Mk01LWuyxGH+8OnkmyVkmUC38w+fkNz1SDTOHz4hB8Z6nLaecEP3x3Jjpv0xTp18yygd1y8enYysqkC/1q8eITlTTfTHqZPpepnmyCg9hFt6WH+x7eQjc6o363HGNmTl1rNiGzLQ+2LNfBTeOIVcrmaJddIc8gHLTkZm3ZgP2IWsuJrlQ3YhOetxPgqnq1k+aBKy4mo27g3adPKKvR4Pe4M2IRN9YlaOe4NWazIWKwLdrVezHPWQy9WMw9P+VM/odzIwt57nV4U9LKabdTXLj8ItqiF7jsItup0MzLfCdH6qZ3RDdhyFW7TXJNtSoBK1kLWWAqHXyUpLgdAL2fiqsIdeyEpLgf+UAi2WAqHVyciq2lgKhFbIakuBEA/ZaikQ8p1stBQI+ZCCW8+KfMhGS4EQDSl1NcuR7mRk1o1UgWxIoatZjmzIDkuBEAvZaykQcp3stBQIuZCdlgIhuSZjsQJYCoRISAlLgZDppIClQEhNd7elQHSH1DoKt/R3UshSIPpDKh2FWyTWpIilQHSFlLQUiL5OCloKRF9IhVeFPfpCCloK/JhGpC0FoqeTkVXFtBSInpCilgLRFFLDUiDaOqlgKRBtIY22npW2kAqWAlEdkn01C/SbhGjpZGRVpb6jcEt9SIOrWU59SCVLgagKqWkpEHWdVLQUiLqQipYCUbsmY7Gi0VIg2CG1LQWC30llS4GomW7e1eyv3NazwgpZYykkrmY5vE4aWAoEL2Rir0fxqZ7h/j8Oz1L8kN0fV4qdtLIUiPJ0G1kKRDmk8avCHgOjwsRS4AgAS0uBKHUyEgcBS4EohTSzFIizIa0tBeJ8Jx/Zb4Uqp8yWATzc7WqWM4CHm1oKxG5ID0uBONfJSByS/lTPDGcebm4pEPshHSwF4iSkl6VAnHbSyVIgTkM6WQrE3pqMVELBUiBehfS0FIjXnQyXcxRuyafbzVIg/of0thSIl046WwrEsHm4q6VAbA2Gq6VALJ28BEuBOE73BVgKxDHkBVgKxPD86W4pEP8AISoShgFqDdYAAAAASUVORK5CYII="
                    },
                    {
                        "fileName": "Via-T_white.png",
                        "fileContent": "iVBORw0KGgoAAAANSUhEUgAAAGAAAABACAYAAADlNHIOAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA0NpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDkuMS1jMDAyIDc5LmYzNTRlZmM3MCwgMjAyMy8xMS8wOS0xMjowNTo1MyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIDI1LjYgKDIwMjQwMjI5Lm0uMjUyNyA1ZjI1NWYxKSAgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RTA3OEFFODNBOEJGMTFFRjg5M0VBQkY5M0Q2NzU3NDUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RTA3OEFFODRBOEJGMTFFRjg5M0VBQkY5M0Q2NzU3NDUiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpFMDc4QUU4MUE4QkYxMUVGODkzRUFCRjkzRDY3NTc0NSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpFMDc4QUU4MkE4QkYxMUVGODkzRUFCRjkzRDY3NTc0NSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PsTdoF4AAAHVSURBVHja7Jw9SwMxAIYvYkWFtoN1FBzEH1C6uDn7A1x1cRQRpKg4iooI/gAHcfQP6OTsonUXBHfdBBWFxvfaOCj9SNqe/fB54D3SNpfr5UnvkqFnrLURdI8hugABCAAEIAAQgAD4e4YTbn9MMZ5135WyT0VjXJPWGveGrb60IQNv1LNu3OhbUh1kElyIbSl7AfXjL/Ki3CvXyqlyV1fA7vFCZM2ZO4ulaGf9osG55JVlZU6ZVdIBAyNmW9nvt0vQdOhgUDJKQVlVSsq5kq2tyxxpm6ukWq5F1rVx69osuGOYhM+lZy5B7bKoTCnzysevzybrlL8ZUS7dqOcm3AZxBxZb2K/Y653fT7OgNSUVUD/l9mEa2iFy7kbqS97tg4AOkk+oLgI8mQj8xfz7hdiJchUwzTtoMj3MBBw77bHm2FQePdt76EcBNy6+rCgzTdYJIWuKZh16yCXoJ+UBPdbA3AMGEgQgAAGAAAQAAhAACEAAIAABgAAEAAIQAAhAACAAAYAABAACEAAIQAAgAAGAAAQAAhAACEBAN3mqU0ZAi5SCahu7oe1zJdVycsdKENNjj60cb/DZp4vP42pSUeN/1r8iALgJIwAQgAAEAAIQAN3iS4ABAMnNUZZFngQCAAAAAElFTkSuQmCC"
                    },
                    {
                        "fileName": "ico-disabled.png",
                        "fileContent": "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"
                    }
                ]
            }
        """.trimIndent()
    }

    fun getConfigFile(): String {
        return """
            [
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