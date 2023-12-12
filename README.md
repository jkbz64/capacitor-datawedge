# Capacitor DataWedge - community maintained plugin

![capacitor-version](https://img.shields.io/badge/Capacitor-v5-lightgreen)
![capacitor-version](https://img.shields.io/badge/Capacitor-v3--v4-orange)
![version](https://img.shields.io/npm/v/capacitor-datawedge)
![downloads](https://img.shields.io/npm/dm/capacitor-datawedge)
![contributors](https://img.shields.io/github/contributors/jkbz64/capacitor-datawedge)
![license](https://img.shields.io/npm/l/capacitor-datawedge)

This plugin allows you to simply gain access to receiving barcode data and use some api methods from the DataWedge API designed for Capacitor with Zebra devices.

`capacitor-datawedge` project is ***unofficial*** and completely community maintained, do not mistake this plugin with the official [Zebra Scanner](https://ionic.io/docs/zebra-datawedge) plugin.

It is advised to use official plugin for full compatibility and guaranteed support, this project was created before the official plugin existed and is maintained by people who use it - ***it does not aim for full compatibility***, features are added when someone cares enough to share the changes with other people in the true open source nature.

## Install

```bash
npm install capacitor-datawedge
npx cap sync
```

The last supported version for Capacitor v3 is `capacitor-datawedge@0.1.3`

The last supported version for Capacitor v4 is `capacitor-datawedge@0.2.1`

## Usage

Enable intent output in your DataWedge profile, set `Intent delivery` to `Broadcast intent` and set `Intent action` to `com.capacitor.datawedge.RESULT_ACTION`

```js
import { DataWedge } from 'capacitor-datawedge';

// Register scan listener to receive barcode data
DataWedge.addListener('scan', event => {
  console.log(event.data);
});

// Scanning the barcode using physical trigger should fire up your scan callback!
// Check API for more methods
```

## API

<docgen-index>

* [`enable()`](#enable)
* [`disable()`](#disable)
* [`enableScanner()`](#enablescanner)
* [`disableScanner()`](#disablescanner)
* [`startScanning()`](#startscanning)
* [`stopScanning()`](#stopscanning)
* [`registerBroadcastReceiver(...)`](#registerbroadcastreceiver)
* [`sendBroadcastWithExtras(...)`](#sendbroadcastwithextras)
* [`addListener('scan' | 'broadcast', ...)`](#addlistenerscan--broadcast)
* [`removeAllListeners()`](#removealllisteners)
* [`__registerReceiver()`](#__registerreceiver)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

Every broadcasted intent assumes `com.symbol.datawedge.api` package as default.

Package name can be changed by modyfing `DATAWEDGE_PACKAGE` variable [here](android/src/main/java/com/jkbz/capacitor/datawedge/DataWedge.java)

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### enable()

```typescript
enable() => Promise<void>
```

Enables DataWedge

Broadcasts intent action with `.ENABLE_DATAWEDGE` extra set to `true`

**Since:** 0.0.3

--------------------


### disable()

```typescript
disable() => Promise<void>
```

Disables DataWedge

Broadcasts intent action with `.ENABLE_DATAWEDGE` extra set to `false`

**Since:** 0.0.3

--------------------


### enableScanner()

```typescript
enableScanner() => Promise<void>
```

Enables physical scanner

Broadcasts intent action with `.SCANNER_INPUT_PLUGIN` extra set to `ENABLE_PLUGIN`

**Since:** 0.0.3

--------------------


### disableScanner()

```typescript
disableScanner() => Promise<void>
```

Disables physical scanner

Broadcasts intent action with `.SCANNER_INPUT_PLUGIN` extra set to `DISABLE_PLUGIN`

**Since:** 0.0.3

--------------------


### startScanning()

```typescript
startScanning() => Promise<void>
```

Starts software scanning trigger

Broadcasts intent action with `.SOFT_SCAN_TRIGGER` extra set to `START_SCANNING`

**Since:** 0.1.2

--------------------


### stopScanning()

```typescript
stopScanning() => Promise<void>
```

Stops software scanning trigger

Broadcasts intent action with `.SOFT_SCAN_TRIGGER` extra set to `STOP_SCANNING`

**Since:** 0.1.2

--------------------


### registerBroadcastReceiver(...)

```typescript
registerBroadcastReceiver(filter: BroadcastReceiverFilter) => Promise<void>
```

Register broadcast receiver

| Param        | Type                                                                        |
| ------------ | --------------------------------------------------------------------------- |
| **`filter`** | <code><a href="#broadcastreceiverfilter">BroadcastReceiverFilter</a></code> |

**Since:** 0.3.0

--------------------


### sendBroadcastWithExtras(...)

```typescript
sendBroadcastWithExtras(intent: BroadcastIntent) => Promise<void>
```

Send broadcast with extras

| Param        | Type                                                        |
| ------------ | ----------------------------------------------------------- |
| **`intent`** | <code><a href="#broadcastintent">BroadcastIntent</a></code> |

**Since:** 0.3.0

--------------------


### addListener('scan' | 'broadcast', ...)

```typescript
addListener(eventName: 'scan' | 'broadcast', listenerFunc: ScanListener | BroadcastListener) => Promise<PluginListenerHandle> & PluginListenerHandle
```

Listen for successful barcode readings

***Notice:*** Requires intent action to be set to `com.capacitor.datawedge.RESULT_ACTION` in current DataWedge profile (it may change in the future)

| Param              | Type                                                                                                        |
| ------------------ | ----------------------------------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'scan' \| 'broadcast'</code>                                                                          |
| **`listenerFunc`** | <code><a href="#scanlistener">ScanListener</a> \| <a href="#broadcastlistener">BroadcastListener</a></code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

**Since:** 0.1.0

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

Remove all listeners

**Since:** 0.3.0

--------------------


### __registerReceiver()

```typescript
__registerReceiver() => Promise<void>
```

Internal method to register intent broadcast receiver

THIS METHOD IS FOR INTERNAL USE ONLY

**Since:** 0.1.3

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### ScanListenerEvent

| Prop       | Type                        | Description     | Since |
| ---------- | --------------------------- | --------------- | ----- |
| **`data`** | <code>string</code>         | Data of barcode | 0.1.0 |
| **`type`** | <code>string \| null</code> | Type of barcode | 0.2.1 |


### Type Aliases


#### BroadcastReceiverFilter

<code>{ filterActions: string[]; filterCategories: string[]; }</code>


#### BroadcastIntent

<code>{ action: string; extras: <a href="#jsonobject">JsonObject</a>; }</code>


#### JsonObject

<code>{ [key: string]: | string | number | boolean | <a href="#jsonobject">JsonObject</a> | string[] | number[] | boolean[] | JsonObject[]; }</code>


#### ScanListener

<code>(state: <a href="#scanlistenerevent">ScanListenerEvent</a>): void</code>


#### BroadcastListener

<code>(state: <a href="#jsonobject">JsonObject</a>): void</code>

</docgen-api>

## License

**BSD-3-Clause**

```
Copyright (c) 2021-2023, jkbz64 and contributors
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of the copyright holder nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```
