import { Capacitor, registerPlugin } from '@capacitor/core';

import type { DataWedgePlugin } from './definitions';

const DataWedge = registerPlugin<DataWedgePlugin>('DataWedge', {
  web: () => import('./web').then(m => new m.DataWedgeWeb()),
});

if (Capacitor.isNativePlatform()) {
  DataWedge.__registerReceiver();
}

export * from './definitions';
export { DataWedge };
