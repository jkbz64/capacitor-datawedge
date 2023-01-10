import type { PluginListenerHandle } from '@capacitor/core';

export interface ScanListenerEvent {
  /**
   * Data of barcode
   *
   * @since 0.1.0
   */
  data: string;
}

export type ScanListener = (state: ScanListenerEvent) => void;

export interface DataWedgePlugin {
  /**
   * Enables DataWedge
   *
   * Broadcasts intent action with `.ENABLE_DATAWEDGE` extra set to `true`
   *
   * @since 0.0.3
   */
  enable(): Promise<void>;

  /**
   * Disables DataWedge
   *
   * Broadcasts intent action with `.ENABLE_DATAWEDGE` extra set to `false`
   *
   * @since 0.0.3
   */
  disable(): Promise<void>;

  /**
   * Enables physical scanner
   *
   * Broadcasts intent action with `.SCANNER_INPUT_PLUGIN` extra set to `ENABLE_PLUGIN`
   *
   * @since 0.0.3
   */
  enableScanner(): Promise<void>;

  /**
   * Disables physical scanner
   *
   * Broadcasts intent action with `.SCANNER_INPUT_PLUGIN` extra set to `DISABLE_PLUGIN`
   *
   * @since 0.0.3
   */
  disableScanner(): Promise<void>;

  /**
   * Starts software scanning trigger
   *
   * Broadcasts intent action with `.SOFT_SCAN_TRIGGER` extra set to `START_SCANNING`
   *
   * @since 0.1.2
   */
  startScanning(): Promise<void>;

  /**
   * Stops software scanning trigger
   *
   * Broadcasts intent action with `.SOFT_SCAN_TRIGGER` extra set to `STOP_SCANNING`
   *
   * @since 0.1.2
   */
  stopScanning(): Promise<void>;

  /**
   * Returns boolean if scanner is connected
   *
   * @since 0.1.3
   */
  isScannerAvailable(): Promise<boolean>;

  /**
   * Listen for successful barcode readings
   *
   * ***Notice:*** Requires intent action to be set to `com.capacitor.datawedge.RESULT_ACTION` in current DataWedge profile (it may change in the future)
   *
   * @since 0.1.0
   */
  addListener(
    eventName: 'scan',
    listenerFunc: ScanListener,
  ): Promise<PluginListenerHandle> & PluginListenerHandle;

  /**
   * Internal method to register intent broadcast receiver
   * THIS METHOD IS FOR INTERNAL USE ONLY
   *
   * @since 0.1.3
   * @private
   */
  __registerReceiver(): Promise<void>;

  /**
   * Internal method to enumerate through the receivers
   * THIS METHOD IS FOR INTERNAL USE ONLY
   *
   * @since 0.1.4
   * @private
   */
  __enumerateScanners(): Promise<void>;
}
