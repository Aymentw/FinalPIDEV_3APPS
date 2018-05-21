<?php
/**
 * Created by PhpStorm.
 * User: selim
 * Date: 10/04/2018
 * Time: 21:53
 */

namespace UtilisateurBundle\Entity;

use Doctrine\ORM\Mapping as ORM;


/**
 * Covoiturage
 *
 * @ORM\Table(name="covsignale")
 * @ORM\Entity
 */
class CovSignale
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \UtilisateurBundle\Entity\Covoiturage
     *
     * @ORM\ManyToOne(targetEntity="UtilisateurBundle\Entity\Covoiturage")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_c", referencedColumnName="id")
     * })
     */
    private $idC;


    /**
     * @var \UtilisateurBundle\Entity\User
     *
     * @ORM\ManyToOne(targetEntity="UtilisateurBundle\Entity\User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_u", referencedColumnName="id")
     * })
     */
    private $idU;
    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return Covoiturage
     */
    public function getIdC()
    {
        return $this->idC;
    }

    /**
     * @param Covoiturage $idC
     */
    public function setIdC($idC)
    {
        $this->idC = $idC;
    }

    /**
     * @return User
     */
    public function getIdU()
    {
        return $this->idU;
    }

    /**
     * @param User $idU
     */
    public function setIdU($idU)
    {
        $this->idU = $idU;
    }

}