/**
 * Created by brittanyregrut on 11/17/15.
 */
public class Entry
{
  private String firstName;
  private String lastName;
  private String plotNumber;
  private String dateDeceased;
  private String section;
  private String graveNumber;
  private String intermentNumber;
  private String pInt;
  private String liner;
  private String CGC;
  private String RMF;
  private String monument;
  private String planting;
  private String veteran;
  private String cremated;
  private String foundations;
  private String monumentNotes;
  private String cgcNotes;
  private String rmfNotes;
  private String linerNotes;

  public Entry(String fn, String ln, String pn, String date, String sec, String grave, String intermentNum, String pnInt, String pnLiner, String pnCGC, String pnRMF, String mon, String ppPlanting, String vet, String crem, String found, String mnotes, String cnotes, String rnotes, String lnotes)
  {
    this.firstName = fn;
    this.lastName = ln;
    this.plotNumber = pn;
    this.dateDeceased = date;
    this.section = sec;
    this.graveNumber = grave;
    this.intermentNumber = intermentNum;
    this.pInt = pnInt;
    this.liner = pnLiner;
    this.CGC = pnCGC;
    this.RMF = pnRMF;
    this.monument = mon;
    this.planting = ppPlanting;
    this.veteran = vet;
    this.cremated = crem;
    this.foundations = found;
    this.monumentNotes = mnotes;
    this.cgcNotes = cnotes;
    this.rmfNotes = rnotes;
    this.linerNotes = lnotes;
  }

  //Getter methods
  public String getFirstName()
  {
    if (firstName == null)
    {
      return " ";
    }
    else
    {
      return firstName;
    }
  }

  public String getLastName()
  {
    if (lastName == null)
    {
      return " ";
    }
    else
    {
      return lastName;
    }
  }

  public String getCGC()
  {
    if (CGC == null)
    {
      return " ";
    }
    else
    {
      return CGC;
    }
  }

  public String getCremated()
  {
    if (cremated == null)
    {
      return " ";
    }
    else
    {
      return cremated;
    }
  }

  public String getDateDeceased()
  {
    if (dateDeceased == null)
    {
      return " ";
    }
    else
    {
      return dateDeceased;
    }
  }

  public String getFoundations()
  {
    if (foundations == null)
    {
      return " ";
    }
    else
    {
      return foundations;
    }
  }

  public String getGraveNumber()
  {
    if (graveNumber == null)
    {
      return " ";
    }
    else
    {
      return graveNumber;
    }
  }

  public String getIntermentNumber()
  {
    if (intermentNumber == null)
    {
      return " ";
    }
    else
    {
      return intermentNumber;
    }
  }

  public String getLiner()
  {
    if (liner == null)
    {
      return " ";
    }
    else
    {
      return liner;
    }
  }

  public String getMonument()
  {
    if (monument == null)
    {
      return " ";
    }
    else
    {
      return monument;
    }
  }

  public String getpInt()
  {
    if (pInt == null)
    {
      return " ";
    }
    else
    {
      return pInt;
    }
  }

  public String getPlanting()
  {
    if (planting == null)
    {
      return " ";
    }
    else
    {
      return planting;
    }
  }

  public String getPlotNumber()
  {
    if (plotNumber == null)
    {
      return " ";
    }
    else
    {
      return plotNumber;
    }
  }

  public String getRMF()
  {
    if (RMF == null)
    {
      return " ";
    }
    else
    {
      return RMF;
    }
  }

  public String getSection()
  {
    if (section == null)
    {
      return " ";
    }
    else
    {
      return section;
    }
  }

  public String getVeteran()
  {
    if (veteran == null)
    {
      return " ";
    }
    else
    {
      return veteran;
    }
  }

  public String getMonumentNotes()
  {
    return monumentNotes;
  }

  public String getCgcNotes()
  {
    return cgcNotes;
  }

  public String getRmfNotes()
  {
    return rmfNotes;
  }

  public String getLinerNotes()
  {
    return linerNotes;
  }


  /**
   * Prints entry to a text file
   */
  public String toString()
  {
    return (this.lastName + ", " + this.firstName + ": " + this.plotNumber + "-" +
        this.section + "-" + this.graveNumber + ", " + this.intermentNumber
    );
  }
}
