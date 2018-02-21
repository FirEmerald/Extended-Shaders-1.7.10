Please excuse the ugliness in the github readme viewer, it breaks my readme and I don't know why.

API for the Extended Shaders mod, for Minecraft 1.7.10
The purpose of this API, and it's containing coremod, Extended Shaders, is to allow mods to use shaders and post-processors inside Minecraft without needing to learn how to make a coremod, and without sacrificing compatibility with other mods using this shader system.
It is designed to allow multiple mods to "attach" shader uniforms, variables, constants, and code without conflicting with each other, in a way not provided by GLSL itself.
It is NOT intended to be used by someone without a good understanding of GLSL.

HOW TO USE:	
	You will need to add these API classes to your project, PRESERVING the package names.
	HOW TO MAKE A SHADER DATA FILE:
		1. create a file in your mod's assets, containing a list of the uniforms, variables, and constants for the vertex shader.
		2. create a file in your mod's assets, containing the code for the vertex shader.
		3. create a file in your mod's assets, containing a list of the uniforms, variables, and constants for the fragment shader.
		4. create a file in your mod's assets, containing the code for the fragment shader. you will work via modifying gl_FragData[0] (the fragments color) or gl_FragData[1] (the fragment's eye position, used for certain post-proccesing).
		5. create the ShaderData instance:
			shaderData = new ShaderData(new ResourceLocation(modid, vertuniforms), new ResourceLocation(modid, vertcode), new ResourceLocation(modid, fraguniforms), new ResourceLocation(modid, fragcode));
			*do note that any of those resourcelocations can be null, representing no code.*
	HOW TO MAKE A SINGLE-STATE SHADER
		1. follow steps 1-4 in "HOW TO MAKE A SHADER DATA FILE".
		2. create the instance:
			shaderSingle = new ShaderSingle(new ResourceLocation(modid, vertuniforms), new ResourceLocation(modid, vertcode), new ResourceLocation(modid, fraguniforms), new ResourceLocation(modid, fragcode), priority);
	HOW TO MAKE A MULTI-STATE SHADER
		1. follow steps 1-5 in "HOW TO MAKE A SHADER DATA FILE" for each state.
		2. create the instance:
			shaderSingle = new ShaderMulti(priority, shaderData1, shaderData2...);
	HOW TO HANDLE SHADERS:
		to turn a shader ON, do this:
			ShaderRegistry.addShader(shader);
		to turn a shader OFF, do this:
			ShaderRegistry.removeShader(shader);
		to get a shader's state:
			state = ShaderRegistry.getShaderState(shader);
		to set a shader's state:
			ShaderRegistry.setShaderState(shader, state);
	HOW TO MAKE A POST-PROCESSOR:
		1. create a file in your mod's assets, containing a list of the post-processor's uniforms and constants.
		2. create a file in your mod's assets, conatining the post-processor's code.
			It is IMPORTANT that you do not use blending - alpha MUST be 0 or 1. you can also discard fragments if you would like.
		3. create a new PostProcessor instance, like this
			postProcessor = new PostProcessor(new ResourceLocation(modid, uniformslocation), new ResourceLocation(modid, codelocation), priority);
			*do note that the uniforms location can be null, representing no code.*
		4. to turn it ON use this:
			PostProcessorRegistry.addPostProcessor(postProcessor);
		5. to turn it OFF use this:
			PostProcessorRegistry.removePostProcessor(postProcessor);
See the classes inside the API for more advanced usage.

SHADER UNIFORMS:
varying vec4 fragCol; //fragment color.
varying vec3 fragNorm; //fragment normal.
varying vec4 eyePos; //fragment's eye position.
varying vec4 objectPos; //fragment's object position.
uniform bool useFog; //is fog enabled?
uniform int fogMode; //advanced usage.
uniform bool isAlias; //is this the block texture alias?
uniform bool isEntity; //is this an entity?
uniform bool useNormals; //is normal-based lighting enabled?
uniform sampler2D tex0; //texture unit 0
uniform bool useTex0; //is texture unit 0 enabled?
uniform bool texGen0_s; //advanced usage.
uniform int texGenMode0_s; //advanced usage.
uniform bool texGen0_t; //advanced usage.
uniform int texGenMode0_t; //advanced usage.
uniform bool texGen0_p; //advanced usage.
uniform int texGenMode0_p; //advanced usage.
uniform bool texGen0_q; //advanced usage.
uniform int texGenMode0_q; //advanced usage.
uniform sampler2D tex1; //texture unit 1
uniform bool useTex1; //is texture unit 1 enabled?
uniform bool texGen1_s; //advanced usage.
uniform int texGenMode1_s; //advanced usage.
uniform bool texGen1_t; //advanced usage.
uniform int texGenMode1_t; //advanced usage.
uniform bool texGen1_p; //advanced usage.
uniform int texGenMode1_p; //advanced usage.
uniform bool texGen1_q; //advanced usage.
uniform int texGenMode1_q; //advanced usage.
uniform sampler2D tex2; //texture unit 2
uniform bool useTex2; //is texture unit 2 enabled?
uniform bool texGen2_s; //advanced usage.
uniform int texGenMode2_s; //advanced usage.
uniform bool texGen2_t; //advanced usage.
uniform int texGenMode2_t; //advanced usage.
uniform bool texGen2_p; //advanced usage.
uniform int texGenMode2_p; //advanced usage.
uniform bool texGen2_q; //advanced usage.
uniform int texGenMode2_q; //advanced usage.
uniform sampler2D tex3; //texture unit 3
uniform bool useTex3; //is texture unit 3 enabled?
uniform bool texGen3_s; //advanced usage.
uniform int texGenMode3_s; //advanced usage.
uniform bool texGen3_t; //advanced usage.
uniform int texGenMode3_t; //advanced usage.
uniform bool texGen3_p; //advanced usage.
uniform int texGenMode3_p; //advanced usage.
uniform bool texGen3_q; //advanced usage.
uniform int texGenMode3_q; //advanced usage.
uniform sampler2D tex4; //texture unit 4
uniform bool useTex4; //is texture unit 4 enabled?
uniform bool texGen4_s; //advanced usage.
uniform int texGenMode4_s; //advanced usage.
uniform bool texGen4_t; //advanced usage.
uniform int texGenMode4_t; //advanced usage.
uniform bool texGen4_p; //advanced usage.
uniform int texGenMode4_p; //advanced usage.
uniform bool texGen4_q; //advanced usage.
uniform int texGenMode4_q; //advanced usage.
uniform sampler2D tex5; //texture unit 5
uniform bool useTex5; //is texture unit 5 enabled?
uniform bool texGen5_s; //advanced usage.
uniform int texGenMode5_s; //advanced usage.
uniform bool texGen5_t; //advanced usage.
uniform int texGenMode5_t; //advanced usage.
uniform bool texGen5_p; //advanced usage.
uniform int texGenMode5_p; //advanced usage.
uniform bool texGen5_q; //advanced usage.
uniform int texGenMode5_q; //advanced usage.
uniform sampler2D tex6; //texture unit 6
uniform bool useTex6; //is texture unit 6 enabled?
uniform bool texGen6_s; //advanced usage.
uniform int texGenMode6_s; //advanced usage.
uniform bool texGen6_t; //advanced usage.
uniform int texGenMode6_t; //advanced usage.
uniform bool texGen6_p; //advanced usage.
uniform int texGenMode6_p; //advanced usage.
uniform bool texGen6_q; //advanced usage.
uniform int texGenMode6_q; //advanced usage.
uniform sampler2D tex7; //texture unit 7
uniform bool useTex7; //is texture unit 7 enabled?
uniform bool texGen7_s; //advanced usage.
uniform int texGenMode7_s; //advanced usage.
uniform bool texGen7_t; //advanced usage.
uniform int texGenMode7_t; //advanced usage.
uniform bool texGen7_p; //advanced usage.
uniform int texGenMode7_p; //advanced usage.
uniform bool texGen7_q; //advanced usage.
uniform int texGenMode7_q; //advanced usage.

POST PROCCESSOR UNIFORMS:
uniform sampler2D tex0; //the scene
uniform sampler2D tex1; //the fragment's eye pos
varying vec2 texCoords; //the coordinates
uniform float dx; //the X size of a fragment relative to the scene size
uniform float dy; //the Y size of a fragment relative to the scene size
uniform int eye; //-1 for no anaglyph, 0 for left eye, 1 for right. you probably won't need this.
