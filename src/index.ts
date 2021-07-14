import { registerPlugin } from '@capacitor/core';

import type { DataWedgePlugin } from './definitions';

const DataWedge = registerPlugin<DataWedgePlugin>('DataWedge', {
  web: () => import('./web').then(m => new m.DataWedgeWeb()),
});

export * from './definitions';
export { DataWedge };
