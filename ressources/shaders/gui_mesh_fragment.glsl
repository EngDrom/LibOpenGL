#version 330 core

in vec2 passTCoord;
in vec4 passColor;

out vec4 outColor;

uniform sampler2D tex;
uniform bool isText;

void main() {
	if (isText) {
		outColor = texture(tex, passTCoord);
	} else {
		outColor = passColor;
	}
}
