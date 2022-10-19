package pe.idat.dto;

public class UsuarioDTOResponse {

	private String token;

	public UsuarioDTOResponse() {
	
	}
	
	public UsuarioDTOResponse(String token) {
		super();
		this.token = token;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
