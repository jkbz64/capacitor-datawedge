import { WebPlugin } from '@capacitor/core';

import type { DataWedgePlugin } from './definitions';

export class DataWedgeWeb extends WebPlugin implements DataWedgePlugin {
  async enable(): Promise<void> {
    throw 'DataWedge is not supported on web';
  }

  async disable(): Promise<void> {
    throw 'DataWedge is not supported on web';
  }

  async enableScanner(): Promise<void> {
    throw 'DataWedge is not supported on web';
  }

  async disableScanner(): Promise<void> {
    throw 'DataWedge is not supported on web';
  }
}
