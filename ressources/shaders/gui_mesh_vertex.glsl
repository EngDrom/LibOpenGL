#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec2 tCoord;

out vec2 passTCoord;
out vec4 passColor;

uniform mat4 view;
uniform vec4 color;

void main() {
	gl_Position = view * vec4(position, 1.0);
	passTCoord = tCoord;
	passColor  = color;
}
