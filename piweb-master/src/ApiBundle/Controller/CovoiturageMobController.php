<?php
/**
 * Created by PhpStorm.
 * User: ASUS-PC
 * Date: 23/03/2018
 * Time: 21:14
 */

namespace ApiBundle\Controller;

use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use UtilisateurBundle\Entity\AdressesCov;
use UtilisateurBundle\Entity\Covoiturage;
use UtilisateurBundle\Entity\CovSignale;
use UtilisateurBundle\Entity\User;
use UtilisateurBundle\Form\CovoiturageType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CovoiturageMobController extends Controller
{
    public function ListerAction()
    {

        $em=$this->getDoctrine()->getManager();
        $covoiturages=$em->getRepository("UtilisateurBundle:Covoiturage")->findAll();

        $notifiableRepo = $this->get('doctrine.orm.entity_manager')->getRepository('MgiletNotificationBundle:NotifiableNotification');
        $manager = $this->get('mgilet.notification');
      $all=$notifiableRepo->findAllForNotifiableId($manager->getNotifiableEntity($this->getUser()));

        return $this->render('CovoiturageBundle:Covoiturage:Lister.html.twig',array(
            "covoiturages"=>$covoiturages,
            "notifiableEntity"=>$this->getUser(),
            "notifiableNotifications"=>$all
        ));


    }
    public function Lister_adminAction()
    {

        $em=$this->getDoctrine()->getManager();
        $covoiturages=$em->getRepository("UtilisateurBundle:Covoiturage")->findAll();
        $covSs=$em->getRepository(CovSignale::class)->findAll();

        return $this->render('CovoiturageBundle:Covoiturage:Lister_admin.html.twig',array(
            "covoiturages"=>$covoiturages,
            "covSs"=>$covSs,

        ));


    }
    public function AfficherAction(Request $request)
    {
        $current_user=$this->getUser();
        $id=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $covoiturage=$em->getRepository("UtilisateurBundle:Covoiturage")->find($id);
        $depart=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($covoiturage->getDepart());
        $dest=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($covoiturage->getDestination());
        $covS=$em->getRepository(CovSignale::class)->findBy(array("idC"=>$covoiturage,"idU"=>$this->getUser()));
        return $this->render('CovoiturageBundle:Covoiturage:Afficher.html.twig',array(
            "c"=>$covoiturage,
            "depart"=>$depart,
            "dest"=>$dest,
            "current_user"=>$current_user,
            "covS"=>$covS
        ));
    }
    public function AjouterAction(Request $request)
    {
        $covoiturage= new Covoiturage();
        $form=$this->createForm(CovoiturageType::class,$covoiturage);
        $em=$this->getDoctrine()->getManager();
        if($request->isXmlHttpRequest()){
            $AdrC=new AdressesCov();
            $AdrC->setNom($request->get("depart"));
            if($request->get("test")==0) {
                $AdrC=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($AdrC->getNom());
                $serializer= new Serializer(
                  array(
                      new ObjectNormalizer()
                  )
                );
                $data= $serializer->normalize($AdrC);
                return new JsonResponse($data);
            }
           elseif ($request->get("test")>1)
                $AdrC=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($AdrC->getNom());
            $AdrC->setLat($request->get("deplat"));
            $AdrC->setLng($request->get("deplng"));
            $em->persist($AdrC);
        $em->flush();
        }
        $form->handleRequest($request);
        if($form->isValid()){
            $covoiturage->setIdU($this->getUser());
            $covoiturage->setEtat(1);
            $covoiturage->setVue(1);
             $em->persist($covoiturage);
             $em->flush();
             $covoiturage=$em->getRepository(Covoiturage::class)->find($covoiturage);
            $covoiturages=$em->getRepository(Covoiturage::class)->findBy(array(
                "depart"=>$covoiturage->getDepart(),
                "destination"=>$covoiturage->getDestination(),
                "date"=>$covoiturage->getDate()
            ));
            $manager = $this->get('mgilet.notification');
            foreach ($covoiturages as $c) {
              if($c->getType() != $covoiturage->getType())  {

                  $notif = $manager->createNotification("Nouvelle annonce");
                  $notif->setMessage($this->getUser()->getUsername()." a ajouté une annonce qui peut vous intersser ! ");
                  $notif->setLink('/covoiturage/Afficher?id='.$covoiturage->getId());
                   $manager->addNotification(array($c->getIdU()), $notif, true);
              }
            }


             return $this->redirectToRoute("covoiturage_Lister");
        }
        $AdressesCov=$em->getRepository("UtilisateurBundle:AdressesCov")->findAll();


        return $this->render("CovoiturageBundle:Covoiturage:Ajouter.html.twig",array(
            "Form"=>$form->createView(),
            "Adresses"=>$AdressesCov
        ));
    }
    public function ModifierAction(Request $request){
        $IdC=$request->get("IdC");
        $em=$this->getDoctrine()->getManager();
        $covoiturage=$em->getRepository("UtilisateurBundle:Covoiturage")->find($IdC);
        $form=$this->createForm(CovoiturageType::class,$covoiturage);
        $em=$this->getDoctrine()->getManager();
        if($request->isXmlHttpRequest()){
            $AdrC=new AdressesCov();
            $AdrC->setNom($request->get("depart"));
            if($request->get("test")==0) {
                $AdrC=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($AdrC->getNom());
                $serializer= new Serializer(
                    array(
                        new ObjectNormalizer()
                    )
                );
                $data= $serializer->normalize($AdrC);
                return new JsonResponse($data);
            }
            elseif ($request->get("test")>1)
                $AdrC=$em->getRepository("UtilisateurBundle:AdressesCov")->FindByName($AdrC->getNom());
            $AdrC->setLat($request->get("deplat"));
            $AdrC->setLng($request->get("deplng"));
            $em->persist($AdrC);
            $em->flush();
        }
        $form->handleRequest($request);
        if($form->isValid()){
            $covoiturage->setIdU($this->getUser());
            $covoiturage->setEtat(1);
            $covoiturage->setVue(1);
            $em->persist($covoiturage);
            $em->flush();
            return $this->redirectToRoute("covoiturage_Lister");
        }
        $AdressesCov=$em->getRepository("UtilisateurBundle:AdressesCov")->findAll();
        return $this->render("CovoiturageBundle:Covoiturage:Modifier.html.twig",array(
            "Form"=>$form->createView(),
            "Adresses"=>$AdressesCov
        ));
    }
    public function SupprimerAction(Request $request)
    {
        $id=$request->get('IdC');
        $em=$this->getDoctrine()->getManager();
        $covoiturage=$em->getRepository("UtilisateurBundle:Covoiturage")->find($id);
        $em->remove($covoiturage);
        $em->flush();
        return $this->redirectToRoute("covoiturage_Lister");
    }
    public function Supprimer_adminAction(Request $request)
    {

        $id=$request->get('idC');
        $em=$this->getDoctrine()->getManager();
        $covoiturage=$em->getRepository("UtilisateurBundle:Covoiturage")->find($id);
        $covSs=$em->getRepository(CovSignale::class)->findBy(array("idC"=>$covoiturage));
        foreach ($covSs as $cs){
            $em->remove($cs);
            $em->flush();
        }
        $em->remove($covoiturage);
        $em->flush();
        return $this->redirectToRoute("covoiturage_Listeradmin");
    }
    public function ChercherAction(Request $request){
        $em=$this->getDoctrine()->getManager();

        if ($request->isMethod("POST")){
            if ($request->isXmlHttpRequest()) {
                $serializer = new Serializer(
                    array(
                        new ObjectNormalizer()
                    )
                );
                $covoiturages = $em->getRepository("UtilisateurBundle:Covoiturage")->findCustom($request->get('input'),
                    $request->get('date'),$request->get('type'),$request->get('SortBy'));
                $data = $serializer->normalize($covoiturages);
                return new JsonResponse($data);
            }
        }
        return new Response();
    }
    public function SignalerAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $manager = $this->get('mgilet.notification');
        if ($request->isMethod("POST")){
            if ($request->isXmlHttpRequest()) {
               $covS= new CovSignale();
              $covS->setIdU($this->getUser());
              $cov=$em->getRepository(Covoiturage::class)->find($request->get('idC'));
              $covS->setIdC($cov);
              $em->persist($covS);
              $em->flush();
                $notif = $manager->createNotification("Reclamation envoyée !");
                $notif->setMessage(" Votre reclamation concernant l'annonce du covoiturage de ".$cov->getIdU()->getUsername()." 
                a été envoyé avec succes");
                $manager->addNotification(array($this->getUser()), $notif, true);
                $covSs=$em->getRepository(CovSignale::class)->findBy(array("idC"=>$cov));
                if(count($covSs)==5) {
                    $notif = $manager->createNotification("Avertissement !");
                    $notif->setMessage(" Plusieurs etudiants ont signalé l'un des annonces de covoiturage 
                     que vous avez mis sur notre platforme. Veuillez ameliorer votre comportement sinon vous serez
                     penaliser ! ");
                    $manager->addNotification(array($cov->getIdU()), $notif, true);
                }else if(count($covSs)==10){
                    $em->remove($cov);
                    $em->flush();
                }

            }
        }
        return new Response();
    }
    public function getlistAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $covoiturages=$em->getRepository("UtilisateurBundle:Covoiturage")->findAll();
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $data = $serializer->normalize($covoiturages);
        return new JsonResponse($data);

    }
    public function AjoutMobileAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $covoiturage = new Covoiturage();
        $covoiturage->setDepart($request->get("depart"));
        $covoiturage->setDestination($request->get("destination"));
        $time = strtotime('10/16/2003');

        $date = date('Y-m-d',$time);
        $covoiturage->setDate($date);
        $covoiturage->setType($request->get("type"));
        $em->persist($covoiturage);
        $em->flush();
        return new JsonResponse();
    }
    public function getAdrByNameAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $adr=$em->getRepository(AdressesCov::class)->findByName($request->get("nom"));
        $data = $serializer->normalize($adr);
        return new JsonResponse($data);
}
}