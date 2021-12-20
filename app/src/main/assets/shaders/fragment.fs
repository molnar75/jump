precision mediump float;

varying vec2 v_TexCoord;

uniform vec4 vColor;
uniform sampler2D texture_sampler;

void main() {
    gl_FragColor = (vColor * texture2D(texture_sampler, v_TexCoord));
}