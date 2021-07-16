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
}
