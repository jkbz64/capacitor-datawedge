export interface DataWedgePlugin {
  enable(): Promise<void>;
  disable(): Promise<void>;

  enableScanner(): Promise<void>;
  disableScanner(): Promise<void>;
}
