package extendedshaders.api;

/** used to communicate with Extended Shaders non-api code without requiring a direct reference **/
public class Passthrough
{
	/** the current passthrough - DO NOT SET THIS, it is set by Extended Shaders if it is installed. **/
	public static Passthrough instance = new Passthrough();
	/** Loads a PostProcessor's shader - run automatically BY the PostProcessor **/
	public void loadPostProcessor(PostProcessor postProcessor) {}
	/** switch to the current shader states **/
	public void updateShaderStates() {}
}