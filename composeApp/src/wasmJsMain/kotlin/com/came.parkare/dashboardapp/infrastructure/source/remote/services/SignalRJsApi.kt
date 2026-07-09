package com.came.parkare.dashboardapp.infrastructure.source.remote.services

/** Minimal Promise declaration for JS interop (only the methods we need). */
@JsName("Promise")
internal external class SignalRPromise {
    fun `catch`(onRejected: ((JsAny?) -> Unit)?): SignalRPromise
}

/** Global JSON object for stringifying JS payloads. */
@JsName("JSON")
internal external object JsonGlobal {
    fun stringify(value: JsAny?): String
}

// ----- @microsoft/signalr external declarations -----

@JsModule("@microsoft/signalr")
internal external class HubConnectionBuilder {
    fun withUrl(url: String): HubConnectionBuilder
    fun withAutomaticReconnect(): HubConnectionBuilder
    fun build(): HubConnection
}

@JsModule("@microsoft/signalr")
internal external class HubConnection {
    fun on(method: String, callback: ((JsAny?) -> Unit)?)
    fun onclose(callback: ((JsAny?) -> Unit)?)
    fun onreconnecting(callback: (() -> Unit)?)
    fun onreconnected(callback: ((String) -> Unit)?)
    fun start(): SignalRPromise
    fun stop(): SignalRPromise
}
