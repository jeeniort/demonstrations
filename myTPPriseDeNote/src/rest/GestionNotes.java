package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bo.Note;

@Path("/notes")
public class GestionNotes {
	private static List<Note> notes = new ArrayList<Note>();
	private static int i;
	
	static {
		notes.add(new Note(0, "Je prends des notes !"));
		notes.add(new Note(1, "Mon carnet est vraiment magnifique"));
		i = 2;
	}
	
	@GET
	public List<Note> getNotes() {
		return notes;
	}
	
	@POST
	public Note ajouterNote(
			@FormParam("value") String value)
	{
		Note note = new Note(i++, value);
		notes.add(note);
		return note;
	}
	
	@PUT
	@Path("/{id : \\d+}")
	public Note modifierNote(
			@PathParam("id") int id,
			@FormParam("value") String value)
	{
		Note noteAModifier = null;
		for(Note note : notes)
		{
			if(note.getId()==id)
			{
				note.setValue(value);
				noteAModifier=note;
				break;
			}
		}
		return noteAModifier;
	}
	
	@DELETE
	@Path("/{id : \\d+}")
	public boolean supprimerNote(@PathParam("id") int id)
	{
		Note noteASupprimer = null;
		for(Note note : notes)
		{
			if(note.getId()==id)
			{
				noteASupprimer=note;
				break;
			}
		}
		return notes.remove(noteASupprimer);
	}
}
